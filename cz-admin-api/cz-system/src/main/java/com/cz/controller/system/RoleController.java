package com.cz.controller.system;

import cn.hutool.core.lang.Dict;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cz.annotation.Logger;
import com.google.common.collect.Lists;
import com.cz.annotation.DataScope;
import com.cz.entity.*;
import com.cz.exception.BusinessException;
import com.cz.model.query.RoleListQuery;
import com.cz.model.bo.SysRoleBO;
import com.cz.model.vo.ResultVO;
import com.cz.security.utils.SecurityUtil;
import com.cz.service.*;
import com.cz.utils.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Api(tags = "系统：角色管理")
@RestController
@RequestMapping("/system/roles")
public class RoleController {

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysUsersRolesService usersRolesService;

    @Autowired
    private ISysRolesMenusService sysRolesMenusService;


    @Autowired
    private ISysRoleDeptService sysRoleDeptService;



    @ApiOperation(value = "获取角色列表")
    @PreAuthorize("hasAuthority('roles:list')")
    @GetMapping
    public ResultVO<IPage<SysUser>> list(RoleListQuery roleListQuery, Pageable pageable) {
        return ResponseUtil.success(roleService.list(roleListQuery, pageable));
    }


    @ApiOperation("新增角色")
    @Logger("新增角色")
    @PreAuthorize("hasAuthority('roles:add')")
    @PostMapping
    public ResultVO add(@Validated @RequestBody SysRoleBO sysRoleBO) {
        roleService.add(sysRoleBO);
        return ResponseUtil.success();
    }

    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("编辑角色")
    @Logger("编辑角色")
    @PreAuthorize("hasAuthority('roles:edit')")
    @PutMapping
    public ResultVO edit(@Validated @RequestBody SysRoleBO sysRoleBO) {
        roleService.edit(sysRoleBO);
        return ResponseUtil.success();
    }


    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("删除角色")
    @Logger("删除角色")
    @PreAuthorize("hasAuthority('roles:del')")
    @DeleteMapping
    public ResultVO delete(@RequestBody List<Integer> ids) {
        roleService.delete(ids);
        return ResponseUtil.success();
    }


    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "编辑用户角色")
    @PreAuthorize("hasAuthority('user:edit')")
    @PostMapping("/user")
    public ResultVO<IPage<SysUser>> update(@RequestBody JSONObject jsonObject) {
        List<SysUsersRoles> sysUsersRoles = JSONArray.parseArray(jsonObject.getString("roles"), SysUsersRoles.class);
        if (sysUsersRoles.size() > 0) {
            usersRolesService.remove(new LambdaQueryWrapper<SysUsersRoles>().eq(SysUsersRoles::getUserId, sysUsersRoles.get(0).getUserId()));
            usersRolesService.saveBatch(sysUsersRoles);
        }
        return ResponseUtil.success();
    }

    @ApiOperation("获取角色自定义部门id")
    @GetMapping(value = "/deptIds")
    public ResultVO<List<Integer>> getCustomDeptIds(Integer roleId) {

        List<SysRoleDept> list = sysRoleDeptService.list(new LambdaQueryWrapper<SysRoleDept>().eq(SysRoleDept::getRoleId, roleId));
        return ResponseUtil.success(list.stream().map(SysRoleDept::getDeptId).collect(Collectors.toList()));
    }


    @ApiOperation("获取用户级别")
    @GetMapping(value = "/level")
    public ResultVO getLevel() {
        return ResponseUtil.success(Dict.create().set("level", roleService.getLevels(null)));
    }



    @ApiOperation("获取用户角色id")
    @GetMapping(value = "/roleIds")
    public ResultVO<List<Integer>> getRoleIds(Long userId) {
        if (Objects.isNull(userId)) {
            userId = SecurityUtil.getLoginUser().getUser().getUserId();
        }
        List<SysUsersRoles> list = usersRolesService.list(new LambdaQueryWrapper<SysUsersRoles>().eq(SysUsersRoles::getUserId, userId));
        return ResponseUtil.success(list.stream().map(SysUsersRoles::getRoleId).collect(Collectors.toList()));
    }

    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("为角色分配菜单")
    @PostMapping("/menu")
    public ResultVO menu(@RequestBody List<SysRolesMenus> list) {
        if (list.size() > 0) {
            sysRolesMenusService.remove(new LambdaQueryWrapper<SysRolesMenus>().eq(SysRolesMenus::getRoleId, list.get(0).getRoleId()));
            if (list.get(0).getMenuId() != null) {
                sysRolesMenusService.saveBatch(list);
            }
        }
        return ResponseUtil.success();
    }
}