package com.cz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cz.entity.TablesInfo;
import com.cz.mapper.TablesInfoMapper;
import com.cz.model.query.TableListQuery;
import com.cz.service.ITablesInfoService;
import com.cz.utils.PageUtil;
import com.cz.utils.QueryUtil;
import com.github.yulichang.query.MPJQueryWrapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
public class ITablesInfoServiceImpl extends ServiceImpl<TablesInfoMapper, TablesInfo> implements ITablesInfoService {
    @Override
    public PageUtil<TablesInfo> getTables(TableListQuery tableListQuery, Pageable pageable) {
        PageUtil<TablesInfo> pageUtil = new PageUtil(pageable);
        MPJQueryWrapper<TablesInfo> wrapper =  QueryUtil.buildWrapper(tableListQuery,TablesInfo.class);
        return this.page(pageUtil,wrapper);
    }
}
