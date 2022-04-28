package com.cz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cz.entity.CodeColumnConfig;
import com.cz.entity.TablesInfo;
import com.cz.model.query.TableListQuery;
import com.cz.utils.PageUtil;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * <p>
 * 代码生成器配置 服务类
 * </p>
 *
 * @author chaizi
 * @since 2022-04-11
 */
public interface ITablesInfoService extends IService<TablesInfo> {

    PageUtil<TablesInfo> getTables(TableListQuery tableListQuery, Pageable pageable);

}
