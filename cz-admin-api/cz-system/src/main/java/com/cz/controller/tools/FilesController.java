package com.cz.controller.tools;

import com.cz.entity.SysFiles;
import com.cz.model.vo.SysFilesVO;
import com.cz.service.ISysFilesService;
import com.cz.model.query.SysFilesQuery;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.cz.annotation.Logger;
import com.cz.utils.ResponseUtil;
import com.cz.model.vo.ResultVO;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author chaixuhong
 * @since 2021-08-12
 */
@Validated
@Api(tags = "文件接口", value = "工具接口")
@RestController
@RequestMapping("/tools/files")
public class FilesController {

    @Autowired
    private ISysFilesService sysFilesService;


    @ApiOperation("上传文件")
    @PreAuthorize("hasAuthority('storage:add')")
    @PostMapping
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", dataType = "__file", name = "files", value = "文件", required = true),
            @ApiImplicitParam(paramType = "form", name = "appCode", value = "应用名称", example = "sys", required = true),
            @ApiImplicitParam(paramType = "form", name = "pathName", value = "多级路径"),
            @ApiImplicitParam(paramType = "form", name = "fileName", value = "文件名称", example = "文件名称"),
    })
    public ResultVO<SysFilesVO> upload(@NotNull @RequestPart("files") MultipartFile multipartFile, @RequestParam(name = "pathName", required = false, defaultValue = "sys") String pathName,
                                       @NotNull @RequestParam("appCode") String appCode, @RequestParam("fileName") @NotNull String fileName) {
        SysFilesVO sysFilesVO = sysFilesService.create(multipartFile, pathName, appCode, fileName);
        return ResponseUtil.success(sysFilesVO);
    }

    @GetMapping
    @ApiOperation("查询文件")
    @PreAuthorize("hasAuthority('storage:list')")
    public ResultVO<IPage<SysFiles>> query(SysFilesQuery query, Pageable pageable) {
        return ResponseUtil.success(sysFilesService.queryAll(query, pageable));
    }

    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("hasAuthority('storage:list')")
    public void download(HttpServletResponse response, SysFilesQuery query) throws IOException {
        sysFilesService.download(sysFilesService.queryAll(query), response);
    }


    @Logger("删除文件")
    @ApiOperation("删除文件")
    @PreAuthorize("hasAuthority('storage:del')")
    @DeleteMapping
    public ResultVO delete(@Validated @RequestBody @NotEmpty(message = "id不能为空") List<Integer> ids) {
        sysFilesService.deleteAll(ids);
        return ResponseUtil.success();
    }

}

