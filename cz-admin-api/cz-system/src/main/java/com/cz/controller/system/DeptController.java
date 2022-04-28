package com.cz.controller.system;

import com.cz.annotation.Logger;
import com.cz.model.bo.SysDeptBO;
import com.cz.model.query.DeptListQuery;
import com.cz.model.vo.DeptVO;
import com.cz.model.vo.ResultVO;
import com.cz.service.ISysDeptService;
import com.cz.utils.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;


@Api(tags = "系统：部门管理")
@RestController
@RequestMapping("/system/dept")
@Validated
public class DeptController {

    @Autowired
    private ISysDeptService sysDeptService;

    @ApiOperation(value = "获取部门")
    @PreAuthorize("hasAnyAuthority('user:list','dept:list')")
    @GetMapping
    public ResultVO<DeptVO> deptList(DeptListQuery deptListQuery) {
        return ResponseUtil.success(sysDeptService.deptTree(deptListQuery));
    }


    @ApiOperation("新增部门")
    @Logger("新增部门")
    @PreAuthorize("hasAuthority('dept:add')")
    @PostMapping
    public ResultVO save(@Validated @RequestBody SysDeptBO sysDeptBO){
        sysDeptService.saveDept(sysDeptBO);
        return ResponseUtil.success();
    }

    @ApiOperation("编辑部门")
    @Logger("编辑部门")
    @PreAuthorize("hasAuthority('dept:edit')")
    @PutMapping
    public ResultVO update(@Validated @RequestBody SysDeptBO sysDeptBO){
        sysDeptService.updateDept(sysDeptBO);
        return ResponseUtil.success();
    }

    @Logger("删除部门")
    @ApiOperation("删除部门")
    @PreAuthorize("hasAuthority('dept:del')")
    @DeleteMapping
    public ResultVO delUser(@Validated @RequestBody @NotEmpty(message = "id不能为空") List<Integer> ids){
        sysDeptService.delDepts(ids);
        return ResponseUtil.success();
    }
}
