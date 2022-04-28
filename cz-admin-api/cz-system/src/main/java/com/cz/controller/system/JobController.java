package com.cz.controller.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cz.annotation.Logger;
import com.cz.entity.SysJob;
import com.cz.model.bo.SysDeptBO;
import com.cz.model.bo.SysJobBO;
import com.cz.model.query.DeptListQuery;
import com.cz.model.query.JobListQuery;
import com.cz.model.vo.DeptVO;
import com.cz.model.vo.ResultVO;
import com.cz.service.ISysDeptService;
import com.cz.service.ISysJobService;
import com.cz.utils.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotEmpty;
import java.util.List;


@Api(tags = "系统：岗位管理")
@RestController
@RequestMapping("/system/job")
@Validated
public class JobController {

    @Autowired
    private ISysJobService jobService;


    @ApiOperation(value = "获取所有岗位")
    @PreAuthorize("hasAuthority('job:list')")
    @GetMapping("/all")
    public ResultVO<List<SysJob>> all() {
        return ResponseUtil.success(jobService.allJobs());
    }

    @ApiOperation(value = "获取岗位")
    @PreAuthorize("hasAuthority('job:list')")
    @GetMapping
    public ResultVO<IPage<SysJob>> list(JobListQuery jobListQuery, @ApiIgnore Pageable pageable) {
        return ResponseUtil.success(jobService.jobs(jobListQuery,pageable));
    }


    @ApiOperation("新增岗位")
    @Logger("新增岗位")
    @PreAuthorize("hasAuthority('job:add')")
    @PostMapping
    public ResultVO save(@Validated @RequestBody SysJobBO sysJobBO){
        jobService.saveJob(sysJobBO);
        return ResponseUtil.success();
    }

    @ApiOperation("编辑岗位")
    @Logger("编辑岗位")
    @PreAuthorize("hasAuthority('job:edit')")
    @PutMapping
    public ResultVO update(@Validated @RequestBody SysJobBO sysJobBO){
        jobService.updateJob(sysJobBO);
        return ResponseUtil.success();
    }

    @Logger("删除岗位")
    @ApiOperation("删除岗位")
    @PreAuthorize("hasAuthority('job:del')")
    @DeleteMapping
    public ResultVO delJobs(@Validated @RequestBody @NotEmpty(message = "id不能为空") List<Integer> ids){
        jobService.delJobs(ids);
        return ResponseUtil.success();
    }
}
