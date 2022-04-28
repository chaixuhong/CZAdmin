package com.cz.controller.system;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cz.annotation.Logger;
import com.cz.entity.SysMenu;
import com.cz.entity.SysRolesMenus;
import com.cz.model.bo.SysMenuBO;
import com.cz.model.vo.ResultVO;
import com.cz.security.utils.SecurityUtil;
import com.cz.service.ISysMenuService;
import com.cz.service.ISysRolesMenusService;
import com.cz.utils.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(tags = "系统：菜单管理")
@RequestMapping("/system/menus")
public class MenuController {

    @Autowired
    private ISysMenuService sysMenuService;

    @Autowired
    private ISysRolesMenusService sysRolesMenusService;

    @ApiOperation("获取菜单列表")
    @PreAuthorize("hasAuthority('menu:list')")
    @GetMapping
    public ResultVO<List<SysMenu>> query(Integer pid){
        return ResponseUtil.success(sysMenuService.getMenus(pid));
    }

    @ApiOperation("新增菜单")
    @PreAuthorize("hasAuthority('menu:add')")
    @Logger("新增菜单")
    @PostMapping
    public ResultVO saveMenus(@RequestBody SysMenuBO sysMenuBO){
        sysMenuService.saveMenu(sysMenuBO);
        return ResponseUtil.success();
    }

    @ApiOperation("编辑菜单")
    @PreAuthorize("hasAuthority('menu:edit')")
    @Logger("编辑菜单")
    @PutMapping
    public ResultVO updateMenus(@Validated @RequestBody SysMenuBO sysMenuBO){
        SysMenu sysMenu = new SysMenu(sysMenuBO);
        sysMenuService.updateById(sysMenu);
        return ResponseUtil.success();
    }

    @ApiOperation("删除菜单")
    @PreAuthorize("hasAuthority('menu:del')")
    @Logger("删除菜单")
    @DeleteMapping
    public ResultVO delMenus(@RequestBody List<Integer> ids){
        return ResponseUtil.success(sysMenuService.delMenu(ids));
    }


    @ApiOperation("获取子菜单id")
    @GetMapping("/child")
    public ResultVO<List<Integer>> getChildIds(@NotNull(message = "id不能为空") @RequestParam Integer id){
        List<Integer> ids=  sysMenuService.getChildIds(id);
        return ResponseUtil.success(ids);
    }


    @ApiOperation("获取角色菜单id")
    @GetMapping("/idsByRoleId")
    public ResultVO<List<Integer>> idsByRoleId(@NotNull(message = "id不能为空") @RequestParam Integer id){
        LambdaQueryWrapper<SysRolesMenus> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysRolesMenus::getRoleId,id);
        return ResponseUtil.success(sysRolesMenusService.list(queryWrapper).stream().map(SysRolesMenus::getMenuId).collect(Collectors.toList()));
    }


}
