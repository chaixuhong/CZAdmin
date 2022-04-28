package com.cz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cz.annotation.DataScope;
import com.cz.entity.SysRole;
import com.cz.entity.SysRoleDept;
import com.cz.entity.SysRolesMenus;
import com.cz.entity.SysUsersRoles;
import com.cz.exception.BusinessException;
import com.cz.mapper.SysRoleMapper;
import com.cz.mapper.SysRolesMenusMapper;
import com.cz.mapper.SysUsersRolesMapper;
import com.cz.model.bo.SysRoleBO;
import com.cz.model.query.RoleListQuery;
import com.cz.security.utils.SecurityUtil;
import com.cz.service.ISysRoleDeptService;
import com.cz.service.ISysRoleService;
import com.cz.utils.PageUtil;
import com.cz.utils.QueryUtil;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author chaizi
 * @since 2021-12-07
 */
@Service
public class SysRoleServiceImpl extends MPJBaseServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Resource
    private SysRoleMapper roleMapper;

    @Resource
    private SysRolesMenusMapper sysRolesMenusMapper;

    @Resource
    private SysUsersRolesMapper sysUsersRolesMapper;

    @Autowired
    private ISysRoleDeptService sysRoleDeptService;

    @Override
    public IPage<SysRole> list(RoleListQuery roleListQuery, Pageable pageable) {
        PageUtil<SysRole> pageUtil = new PageUtil<>(pageable);
        MPJQueryWrapper<SysRole> wrapper = QueryUtil.buildWrapper(roleListQuery,SysRole.class);
        wrapper.ge("level", this.getLevels(null));
        return this.page(pageUtil, wrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(SysRoleBO sysRoleBO) {
        /**
         * 校验权限
         */
        this.getLevels(sysRoleBO.getLevel());
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(sysRoleBO, sysRole);
        this.save(sysRole);
        if (sysRoleBO.getDataScope().equals(DataScope.Type.CUSTOM.name())) {
            if (sysRoleBO.getDepts().size() == 0) {
                throw new BusinessException("部门不能为空");
            }
            List<SysRoleDept> sysRoleDepts = Lists.newArrayList();
            for (Integer deptId : sysRoleBO.getDepts()) {
                SysRoleDept sysRoleDept = new SysRoleDept();
                sysRoleDept.setRoleId(sysRole.getRoleId());
                sysRoleDept.setDeptId(deptId);
                sysRoleDepts.add(sysRoleDept);
            }
            sysRoleDeptService.saveBatch(sysRoleDepts);
        }
    }

    @Override
    public void edit(SysRoleBO sysRoleBO) {
        if (sysRoleBO.getRoleId() == null) {
            throw new BusinessException("id不能为空");
        }
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(sysRoleBO, sysRole);
        sysRole.setUpdateBy(SecurityUtil.getLoginUser().getUsername());
        this.updateById(sysRole);

        if (sysRoleBO.getDataScope().equals(DataScope.Type.CUSTOM.name())) {
            if (sysRoleBO.getDepts().size() == 0) {
                throw new BusinessException("部门不能为空");
            }
            sysRoleDeptService.remove(new LambdaQueryWrapper<SysRoleDept>().eq(SysRoleDept::getRoleId, sysRoleBO.getRoleId()));
            List<SysRoleDept> sysRoleDepts = Lists.newArrayList();
            for (Integer deptId : sysRoleBO.getDepts()) {
                SysRoleDept sysRoleDept = new SysRoleDept();
                sysRoleDept.setRoleId(sysRole.getRoleId());
                sysRoleDept.setDeptId(deptId);
                sysRoleDepts.add(sysRoleDept);
            }
            sysRoleDeptService.saveBatch(sysRoleDepts);
        }
    }

    @Transactional
    @Override
    public void delete(List<Integer> ids) {
        this.removeByIds(ids);
        sysRoleDeptService.remove(new LambdaQueryWrapper<SysRoleDept>().in(SysRoleDept::getRoleId, ids));
        sysRolesMenusMapper.delete(new LambdaQueryWrapper<SysRolesMenus>().in(SysRolesMenus::getRoleId, ids));
        sysUsersRolesMapper.delete(new LambdaQueryWrapper<SysUsersRoles>().in(SysUsersRoles::getRoleId, ids));
    }

    @Override
    public int getLevels(Integer level) {
        List<SysRole> roles = SecurityUtil.getLoginUser().getRoles();
        List<Integer> levels = roles.stream().map(SysRole::getLevel).collect(Collectors.toList());
        int min = Collections.min(levels);
        if (level != null) {
            if (level < min) {
                throw new BusinessException("权限不足，你的角色级别：" + min + "，低于操作的角色级别：" + level);
            }
        }
        return min;
    }


    @Override
    public List<SysRole> getRolesByUserId(Long userId) {
        return roleMapper.getRolesByUserId(userId);
    }


}
