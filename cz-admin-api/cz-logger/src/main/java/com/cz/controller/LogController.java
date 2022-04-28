package com.cz.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cz.annotation.Logger;
import com.cz.entity.SysLog;
import com.cz.model.LogQuery;
import com.cz.model.vo.ResultVO;
import com.cz.service.ISysLogService;
import com.cz.utils.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

/**
 * <p>
 * 系统日志 前端控制器
 * </p>
 *
 * @author chaizi
 * @since 2021-12-03
 */
@Api(tags = "日志：日志操作")
@RestController
@RequestMapping("/logs")
public class LogController {

    @Autowired
    private ISysLogService sysLogService;

    @GetMapping(value = "/loginUser")
    @ApiOperation("当前登录用户日志查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "page", value = "页", type = "Integer", example = "1", required = true),
            @ApiImplicitParam(paramType = "query", name = "size", value = "数量", type = "Integer", example = "20", required = true),
    })
    public ResultVO<IPage<SysLog>> loginUserLog(Principal principal, @ApiIgnore Pageable pageable) {
        IPage<SysLog> sysLogs = sysLogService.findLoginUserLogs(principal.getName(), pageable);
        return ResponseUtil.success(sysLogs);
    }


    @GetMapping("/info")
    @ApiOperation("日志查询")
    @PreAuthorize("hasAuthority('log:list')")
    public ResultVO<IPage<SysLog>> info(LogQuery query, @ApiIgnore Pageable pageable){
        query.setLogType(Logger.Type.INFO.name());
        IPage<SysLog> sysLogs = sysLogService.findLogs(query, pageable);
        return ResponseUtil.success(sysLogs);
    }

    @GetMapping("/error")
    @ApiOperation("日志查询")
    @PreAuthorize("hasAuthority('log:list')")
    public ResultVO<IPage<SysLog>> error(LogQuery query,@ApiIgnore  Pageable pageable){
        query.setLogType(Logger.Type.ERROR.name());
        IPage<SysLog> sysLogs = sysLogService.findLogs(query, pageable);
        return ResponseUtil.success(sysLogs);
    }


    @GetMapping(value = "/error/{logId}")
    @ApiOperation("日志异常详情查询")
    @PreAuthorize("hasAuthority('log:list')")
    public ResultVO<String> error(@PathVariable Long logId){
        return ResponseUtil.success(sysLogService.findLogsException(logId));
    }

    @DeleteMapping(value = "/error")
    @Logger("删除所有ERROR日志")
    @ApiOperation("删除所有ERROR日志")
    @PreAuthorize("hasAuthority('log:del')")
    public ResultVO delAllErrorLog(){
        sysLogService.delAllLog(Logger.Type.ERROR.name());
        return ResponseUtil.success();
    }

    @DeleteMapping(value = "/info")
    @Logger("删除所有INFO日志")
    @ApiOperation("删除所有INFO日志")
    @PreAuthorize("hasAuthority('log:del')")
    public ResultVO delAllInfoLog(){
        sysLogService.delAllLog(Logger.Type.INFO.name());
        return ResponseUtil.success();
    }



    @Logger("导出INFO日志")
    @ApiOperation("导出数据")
    @GetMapping(value = "/info/download")
    @PreAuthorize("hasAuthority('log:list')")
    public void download(HttpServletResponse response, LogQuery query) throws IOException {
        query.setLogType(Logger.Type.INFO.name());
        sysLogService.download(sysLogService.queryAll(query), response);
    }

    @Logger("导出ERROR日志")
    @ApiOperation("导出错误数据")
    @GetMapping(value = "/error/download")
    @PreAuthorize("hasAuthority('log:list')")
    public void downloadErrorLog(HttpServletResponse response, LogQuery query) throws IOException {
        query.setLogType(Logger.Type.ERROR.name());
        sysLogService.download(sysLogService.queryAll(query), response);
    }

}

