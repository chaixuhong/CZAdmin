package com.cz.service;

import com.cz.entity.CodeColumnConfig;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 代码生成字段信息存储 服务类
 * </p>
 *
 * @author chaizi
 * @since 2022-04-11
 */
public interface ICodeColumnConfigService extends IService<CodeColumnConfig> {

    List<CodeColumnConfig> getColumns(String tableName);

    void syncTablesInfo(List<String> tables);
}
