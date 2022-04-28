package com.cz.controller.tools;

import com.cz.entity.SysEmailConfig;
import com.cz.model.bo.SendEmailVO;
import com.cz.service.ISysEmailConfigService;
import com.cz.model.query.SysEmailConfigQuery;
import com.cz.model.bo.SysEmailConfigBO;
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

import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.metadata.IPage;
/**
* @website 邮箱配置管理控制器
* @author chaizi
* @date 2022-04-25
**/
@RestController
@Api(tags = "邮箱配置管理")
@RequestMapping("/tools/email")
@Validated
public class EmailController {

    @Autowired
    private ISysEmailConfigService sysEmailConfigService;


    @GetMapping
    @ApiOperation("查询邮箱配置")
    @PreAuthorize("hasAuthority('sysEmailConfig:list')")
    public ResultVO<IPage<SysEmailConfig>> query(SysEmailConfigQuery query, Pageable pageable){
        return ResponseUtil.success(sysEmailConfigService.queryAll(query,pageable));
    }

    @PostMapping
    @Logger("新增邮箱配置")
    @ApiOperation("新增邮箱配置")
    @PreAuthorize("hasAuthority('sysEmailConfig:add')")
    public ResultVO create(@Validated @RequestBody SysEmailConfigBO resources) throws Exception {
        sysEmailConfigService.create(resources);
        return ResponseUtil.success();
    }

    @PutMapping
    @Logger("修改邮箱配置")
    @ApiOperation("修改邮箱配置")
    @PreAuthorize("hasAuthority('sysEmailConfig:edit')")
    public ResultVO update(@Validated @RequestBody SysEmailConfigBO resources){
        sysEmailConfigService.update(resources);
        return ResponseUtil.success();
    }

    @Logger("删除邮箱配置")
    @ApiOperation("删除邮箱配置")
    @PreAuthorize("hasAuthority('sysEmailConfig:del')")
    @DeleteMapping
    public ResultVO<List<SysEmailConfig>> delete(@Validated @RequestBody @NotEmpty(message = "id不能为空") List<Integer> ids) {
        return ResponseUtil.success(sysEmailConfigService.deleteAll(ids));
    }


    @ApiOperation("发送邮件")
    @PreAuthorize("hasAuthority('sysEmailConfig:send')")
    @PostMapping("/send")
    public ResultVO delete(@Validated @RequestBody SendEmailVO sendEmailVO) {
        sysEmailConfigService.send(sendEmailVO);
        return ResponseUtil.success();
    }
}