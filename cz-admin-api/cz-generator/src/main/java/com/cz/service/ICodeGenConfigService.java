package com.cz.service;

import com.cz.entity.CodeGenConfig;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 代码生成器配置 服务类
 * </p>
 *
 * @author chaizi
 * @since 2022-04-11
 */
public interface ICodeGenConfigService extends IService<CodeGenConfig> {

    CodeGenConfig findByTableName(String tableName);

    List<Map<String, Object>> previewCode(String tableName);

    void saveOrUpdateConfig(CodeGenConfig genConfig);

    void generatorCode(String tableName) throws IOException;

    void downloadCode(String tableName, HttpServletRequest request, HttpServletResponse response);
}
