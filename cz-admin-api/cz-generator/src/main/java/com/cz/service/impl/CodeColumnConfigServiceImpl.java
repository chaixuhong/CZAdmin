package com.cz.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cz.entity.CodeColumnConfig;
import com.cz.mapper.CodeColumnConfigMapper;
import com.cz.mapper.TablesInfoMapper;
import com.cz.service.ICodeColumnConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 代码生成字段信息存储 服务实现类
 * </p>
 *
 * @author chaizi
 * @since 2022-04-11
 */
@Service
public class CodeColumnConfigServiceImpl extends ServiceImpl<CodeColumnConfigMapper, CodeColumnConfig> implements ICodeColumnConfigService {

    @Autowired
    private CodeColumnConfigMapper codeColumnConfigMapper;

    @Override
    public List<CodeColumnConfig> getColumns(String tableName) {
        LambdaQueryWrapper<CodeColumnConfig> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CodeColumnConfig::getTableName, tableName);
        List<CodeColumnConfig> list = this.list(queryWrapper);
        if (CollectionUtil.isEmpty(list)) {
            list = codeColumnConfigMapper.findColumns(tableName);
        }
        return list;
    }

    @Override
    public void syncTablesInfo(List<String> tables) {
        for (String tableName : tables) {
            LambdaQueryWrapper<CodeColumnConfig> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(CodeColumnConfig::getTableName, tableName);
            List<CodeColumnConfig> list = this.list(queryWrapper);
            if (list.isEmpty()) continue;
            List<CodeColumnConfig> queryList = codeColumnConfigMapper.findColumns(tableName);
            this.sync(list, queryList);
        }
    }

    /**
     * 同步比对字段信息
     *
     * @param columnInfos
     * @param columnInfoList
     */
    private void sync(List<CodeColumnConfig> columnInfos, List<CodeColumnConfig> columnInfoList) {
        // 第一种情况，数据库类字段改变或者新增字段
        for (CodeColumnConfig columnInfo : columnInfoList) {
            // 根据字段名称查找
            List<CodeColumnConfig> columns = columnInfos.stream().filter(c -> c.getColumnName().equals(columnInfo.getColumnName())).collect(Collectors.toList());
            // 如果能找到，就修改部分可能被字段
            if (CollectionUtil.isNotEmpty(columns)) {
                CodeColumnConfig column = columns.get(0);
                column.setColumnType(columnInfo.getColumnType());
                column.setExtra(columnInfo.getExtra());
                column.setKeyType(columnInfo.getKeyType());
                if (StringUtils.isBlank(column.getRemark())) {
                    column.setRemark(columnInfo.getRemark());
                }
                this.updateById(column);
            } else {
                // 如果找不到，则保存新字段信息
                this.save(columnInfo);
            }
        }
        // 第二种情况，数据库字段删除了
        for (CodeColumnConfig columnInfo : columnInfos) {
            // 根据字段名称查找
            List<CodeColumnConfig> columns = columnInfoList.stream().filter(c -> c.getColumnName().equals(columnInfo.getColumnName())).collect(Collectors.toList());
            // 如果找不到，就代表字段被删除了，则需要删除该字段
            if (CollectionUtil.isEmpty(columns)) {
                this.removeById(columnInfo);
            }
        }
    }
}
