package com.cz.controller.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cz.annotation.Logger;
import com.cz.entity.SysMenu;
import com.cz.entity.SysUser;
import com.cz.model.bo.SysUserBO;
import com.cz.model.query.UserListQuery;
import com.cz.model.vo.ResultVO;
import com.cz.security.utils.SecurityUtil;
import com.cz.service.ISysUserService;
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
import java.time.LocalDateTime;
import java.util.List;


@Api(tags = "系统：用户管理")
@RestController
@RequestMapping("/system/user")
public class UserController {

    @Autowired
    private ISysUserService sysUserService;

    @ApiOperation(value = "获取用户列表")
    @PreAuthorize("hasAuthority('user:list')")
    @GetMapping
    public ResultVO<IPage<SysUser>> getAllUser(UserListQuery userListQuery, @ApiIgnore Pageable pageable) {
        return ResponseUtil.success(sysUserService.getAllUser(userListQuery, pageable));
    }

    @ApiOperation("新增用户")
    @Logger("新增用户")
    @PreAuthorize("hasAuthority('user:add')")
    @PostMapping
    public ResultVO saveUser(@Validated @RequestBody SysUserBO sysUserBO){
        sysUserService.saveUserInfo(sysUserBO);
        return ResponseUtil.success();
    }

    @ApiOperation("编辑用户")
    @Logger("编辑用户")
    @PreAuthorize("hasAuthority('user:edit')")
    @PutMapping
    public ResultVO updateUser(@Validated @RequestBody SysUserBO sysUserBO){
        sysUserService.updateUserInfo(sysUserBO);
        return ResponseUtil.success();
    }

    @Logger("删除用户")
    @ApiOperation("删除用户")
    @PreAuthorize("hasAuthority('user:del')")
    @DeleteMapping
    public ResultVO delUser(@Validated @RequestBody @NotEmpty(message = "id不能为空") List<Integer> ids){
        sysUserService.delUsers(ids);
        return ResponseUtil.success();
    }
}
