package com.cz.controller.system;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cz.annotation.Logger;
import com.cz.entity.SysDict;
import com.cz.model.bo.SysDictBO;
import com.cz.model.query.DictListQuery;
import com.cz.model.vo.ResultVO;
import com.cz.service.ISysDictService;
import com.cz.utils.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * <p>
 * 数据字典 前端控制器
 * </p>
 *
 * @author chaizi
 * @since 2022-03-24
 */
@Api(tags = "系统：字典管理")
@RestController
@RequestMapping("/system/dict")
@Validated
public class DictController {
    @Autowired
    private ISysDictService dictService;


    @ApiOperation(value = "获取字典列表")
    @PreAuthorize("hasAuthority('dict:list')")
    @GetMapping
    public ResultVO<IPage<SysDict>> list(DictListQuery dictListQuery, @ApiIgnore Pageable pageable) {
        return ResponseUtil.success(dictService.dicts(dictListQuery, pageable));
    }


    @ApiOperation("新增字典")
    @Logger("新增字典")
    @PreAuthorize("hasAuthority('dict:add')")
    @PostMapping
    public ResultVO save(@Validated @RequestBody SysDictBO sysDictBO) {
        dictService.saveDict(sysDictBO);
        return ResponseUtil.success();
    }

    @ApiOperation("编辑字典")
    @Logger("编辑字典")
    @PreAuthorize("hasAuthority('dict:edit')")
    @PutMapping
    public ResultVO update(@Validated @RequestBody SysDictBO sysDictBO) {
        dictService.updateDict(sysDictBO);
        return ResponseUtil.success();
    }

    @Logger("删除字典")
    @ApiOperation("删除字典")
    @PreAuthorize("hasAuthority('dict:del')")
    @DeleteMapping
    public ResultVO delUser(@Validated @RequestBody @NotEmpty(message = "id不能为空") List<Integer> ids) {
        dictService.delDicts(ids);
        return ResponseUtil.success();
    }

    @ApiOperation("查询字典")
    @GetMapping(value = "/all")
    @PreAuthorize("hasAuthority('dict:list')")
    public ResultVO<List<SysDict>> queryAll(){
        return ResponseUtil.success(dictService.list());
    }
}

