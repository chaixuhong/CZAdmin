package com.cz.controller.system;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cz.annotation.Logger;
import com.cz.entity.SysDict;
import com.cz.entity.SysDictDetail;
import com.cz.model.bo.SysDictBO;
import com.cz.model.bo.SysDictDetailBO;
import com.cz.model.query.DictDetailListQuery;
import com.cz.model.query.DictListQuery;
import com.cz.model.vo.ResultVO;
import com.cz.service.ISysDictDetailService;
import com.cz.utils.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 数据字典详情 前端控制器
 * </p>
 *
 * @author chaizi
 * @since 2022-03-30
 */
@Api(tags = "系统：字典管理")
@RestController
@RequestMapping("/system/dict/detail")
@Validated
public class DictDetailController {

    @Resource
    private ISysDictDetailService sysDictDetailService;

    @ApiOperation(value = "获取字典详情列表")
    @PreAuthorize("hasAuthority('dict:list')")
    @GetMapping
    public ResultVO<IPage<SysDictDetail>> list(@Validated DictDetailListQuery query, @ApiIgnore Pageable pageable) {
        return ResponseUtil.success(sysDictDetailService.dictDetails(query, pageable));
    }

    @ApiOperation(value = "通过字典名称获取字典详情列表")
    @PreAuthorize("hasAuthority('dict:list')")
    @GetMapping("/listByName")
    public ResultVO<List<SysDictDetail>> listByName(@NotEmpty String dictName) {
        return ResponseUtil.success(sysDictDetailService.listByName(dictName));
    }


    @ApiOperation("新增字典详情")
    @Logger("新增字典详情")
    @PreAuthorize("hasAuthority('dict:add')")
    @PostMapping
    public ResultVO save(@Validated @RequestBody SysDictDetailBO sysDictDetailBO) {
        sysDictDetailService.saveDictDetail(sysDictDetailBO);
        return ResponseUtil.success();
    }

    @ApiOperation("编辑字典详情")
    @Logger("编辑字典详情")
    @PreAuthorize("hasAuthority('dict:edit')")
    @PutMapping
    public ResultVO update(@Validated @RequestBody SysDictDetailBO sysDictDetailBO) {
        sysDictDetailService.updateDictDetail(sysDictDetailBO);
        return ResponseUtil.success();
    }

    @Logger("删除字典详情")
    @ApiOperation("删除字典详情")
    @PreAuthorize("hasAuthority('dict:del')")
    @DeleteMapping
    public ResultVO delUser(@NotNull(message = "detailId不能为空") Integer detailId) {
        sysDictDetailService.delDictDetail(detailId);
        return ResponseUtil.success();
    }

}

