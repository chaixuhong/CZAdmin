package com.cz.controller;

import com.cz.entity.CodeColumnConfig;
import com.cz.entity.CodeGenConfig;
import com.cz.model.vo.ResultVO;
import com.cz.service.ICodeColumnConfigService;
import com.cz.service.ICodeGenConfigService;
import com.cz.utils.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/generator/config")
@Api(tags = "工具：代码生成器配置管理")
public class GenConfigController {

    @Autowired
    private ICodeGenConfigService codeGenConfigService;

    @Autowired
    private ICodeColumnConfigService codeColumnConfigService;


    @ApiOperation("查询字段信息")
    @GetMapping(value = "/columns")
    public ResultVO<List<CodeColumnConfig>> queryColumns(@RequestParam String tableName) {
        return ResponseUtil.success(codeColumnConfigService.getColumns(tableName));
    }

    @ApiOperation("保存字段配置数据")
    @PutMapping(value = "/columns")
    public ResultVO save(@RequestBody List<CodeColumnConfig> columnInfos) {
        codeColumnConfigService.saveOrUpdateBatch(columnInfos);
        return ResponseUtil.success(columnInfos);
    }

    @ApiOperation("同步字段数据")
    @PostMapping(value = "columns")
    public ResultVO sync(@RequestBody List<String> tables) {
        codeColumnConfigService.syncTablesInfo(tables);
        return ResponseUtil.success();
    }

    @ApiOperation("查询配置")
    @GetMapping
    public ResultVO<CodeGenConfig> info(String tableName) {
        return ResponseUtil.success(codeGenConfigService.findByTableName(tableName));
    }

    @ApiOperation("修改配置")
    @PutMapping
    public ResultVO<CodeGenConfig> update(@Validated @RequestBody CodeGenConfig genConfig) {
        codeGenConfigService.saveOrUpdateConfig(genConfig);
        return ResponseUtil.success(genConfig);
    }
}
