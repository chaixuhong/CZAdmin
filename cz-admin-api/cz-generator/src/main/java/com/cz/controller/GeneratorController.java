package com.cz.controller;

import com.cz.entity.CodeColumnConfig;
import com.cz.entity.TablesInfo;
import com.cz.model.query.TableListQuery;
import com.cz.model.vo.ResultVO;
import com.cz.service.ICodeColumnConfigService;
import com.cz.service.ICodeGenConfigService;
import com.cz.service.ITablesInfoService;
import com.cz.utils.PageUtil;
import com.cz.utils.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Zheng Jie
 * @date 2019-01-02
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/generator")
@Api(tags = "工具：代码生成管理")
public class GeneratorController {

    @Autowired
    private ICodeGenConfigService codeGenConfigService;

    @Autowired
    private ITablesInfoService tablesInfoService;

    @ApiOperation("查询表信息")
    @GetMapping(value = "/tables")
    public ResultVO<PageUtil<TablesInfo>> queryTables(TableListQuery tableListQuery, @ApiIgnore Pageable pageable) {
        return ResponseUtil.success(tablesInfoService.getTables(tableListQuery, pageable));
    }

    @ApiOperation("生成代码")
    @PutMapping
    public ResultVO generator(@RequestBody TableListQuery query) {
        try {
            codeGenConfigService.generatorCode(query.getName());
        } catch (IOException e) {
            return ResponseUtil.error(400, e.getMessage());
        }
        return ResponseUtil.success();
    }

    @ApiOperation("预览代码")
    @GetMapping
    public ResultVO<List<Map<String, Object>>> preview(String tableName) {
        return ResponseUtil.success(codeGenConfigService.previewCode(tableName));
    }

    @ApiOperation("下载代码")
    @PostMapping
    public void download(@RequestBody TableListQuery query,HttpServletRequest request,HttpServletResponse response) {
        codeGenConfigService.downloadCode(query.getName(),request,response);
    }
}
