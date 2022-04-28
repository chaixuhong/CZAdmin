package com.cz.service.impl;

import cn.hutool.core.util.ZipUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cz.entity.CodeColumnConfig;
import com.cz.entity.CodeGenConfig;
import com.cz.exception.BusinessException;
import com.cz.mapper.CodeColumnConfigMapper;
import com.cz.mapper.CodeGenConfigMapper;
import com.cz.service.ICodeGenConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cz.utils.FilesUtil;
import com.cz.utils.GenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;


/**
 * <p>
 * 代码生成器配置 服务实现类
 * </p>
 *
 * @author chaizi
 * @since 2022-04-11
 */
@Service
public class CodeGenConfigServiceImpl extends ServiceImpl<CodeGenConfigMapper, CodeGenConfig> implements ICodeGenConfigService {

    @Autowired
    private CodeColumnConfigMapper codeColumnConfigMapper;

    @Override
    public CodeGenConfig findByTableName(String tableName) {
        LambdaQueryWrapper<CodeGenConfig> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CodeGenConfig::getTableName,tableName);
        CodeGenConfig codeGenConfig = this.getOne(queryWrapper);
        if (Objects.isNull(codeGenConfig)){
            return new CodeGenConfig(tableName);
        }
        return codeGenConfig;
    }

    @Override
    public List<Map<String, Object>> previewCode(String tableName) {
        LambdaQueryWrapper<CodeGenConfig> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CodeGenConfig::getTableName, tableName);
        CodeGenConfig genConfig = this.getOne(wrapper);
        LambdaQueryWrapper<CodeColumnConfig> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CodeColumnConfig::getTableName, tableName);
        List<CodeColumnConfig> columnConfigs = codeColumnConfigMapper.selectList(queryWrapper);
        if(Objects.isNull(genConfig) || columnConfigs.isEmpty()) throw new BusinessException("请先配置生成器");
        List<Map<String, Object>> genList = GenUtil.preview(columnConfigs, genConfig);
        return genList;
    }

    @Override
    public void saveOrUpdateConfig(CodeGenConfig genConfig) {
        String separator = File.separator;
        String[] paths;
        String symbol = "\\";
        if (symbol.equals(separator)) {
            paths = genConfig.getPath().split("\\\\");
        } else {
            paths = genConfig.getPath().split(File.separator);
        }
        StringBuilder api = new StringBuilder();
        for (String path : paths) {
            api.append(path);
            api.append(separator);
            if ("src".equals(path)) {
                api.append("api");
                break;
            }
        }
        genConfig.setApiPath(api.toString());
        this.saveOrUpdate(genConfig);
    }

    @Override
    public void generatorCode(String tableName) throws IOException {
        LambdaQueryWrapper<CodeGenConfig> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CodeGenConfig::getTableName, tableName);
        CodeGenConfig genConfig = this.getOne(wrapper);
        LambdaQueryWrapper<CodeColumnConfig> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CodeColumnConfig::getTableName, tableName);
        List<CodeColumnConfig> columnConfigs = codeColumnConfigMapper.selectList(queryWrapper);
        if(Objects.isNull(genConfig) || columnConfigs.isEmpty()) throw new BusinessException("请先配置生成器");
        GenUtil.generatorCode(columnConfigs, genConfig);
    }

    @Override
    public void downloadCode(String tableName, HttpServletRequest request, HttpServletResponse response) {
        LambdaQueryWrapper<CodeGenConfig> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CodeGenConfig::getTableName, tableName);
        CodeGenConfig genConfig = this.getOne(wrapper);
        LambdaQueryWrapper<CodeColumnConfig> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CodeColumnConfig::getTableName, tableName);
        List<CodeColumnConfig> columnConfigs = codeColumnConfigMapper.selectList(queryWrapper);
        if(Objects.isNull(genConfig) || columnConfigs.isEmpty()) throw new BusinessException("请先配置生成器");
        try {
            File file = new File(GenUtil.download(columnConfigs, genConfig));
            String zipPath = file.getPath() + ".zip";
            ZipUtil.zip(file.getPath(), zipPath);
            FilesUtil.downloadFile(request, response, new File(zipPath), true);
        } catch (IOException e) {
            throw new BusinessException("打包失败");
        }
    }
}
