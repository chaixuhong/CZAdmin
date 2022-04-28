package com.cz.security.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cz.annotation.DataScope;
import com.cz.entity.*;
import com.cz.security.bean.LoginUser;
import com.cz.service.*;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysMenuService sysMenuService;

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysDeptService sysDeptService;

    @Autowired
    private ISysJobService sysJobService;

    @Autowired
    private ISysRoleDeptService sysRoleDeptService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        SysUser user = sysUserService.getOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, userName));
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("用户不存在");
        }
        //TODO: 修改多线程执行，提升效率
        //获取用户权限
        List<GrantedAuthority> grantedAuthorities = getGrantedAuthorities(user);
        //获取用户角色
        List<SysRole> roles = getRoles(user);
        //获取用户部门
        SysDept dept = getDept(user);
        //获取用户岗位
        SysJob job = getJob(user);
        //获取用户数据权限
        List<Integer> deptIds = getDeptIds(roles, dept);

        //权限列表
        List<String> authorities = grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return new LoginUser(user, roles, dept, job.getName(), deptIds, authorities, null, null, null, null, grantedAuthorities);
    }

    /**
     * 获取用户岗位
     *
     * @param user
     * @return
     */
    private SysJob getJob(SysUser user) {
        SysJob sysJob = sysJobService.getOne(new LambdaQueryWrapper<SysJob>().select(SysJob::getName).eq(SysJob::getJobId, user.getJobId()));
        if(Objects.isNull(sysJob)){
            sysJob = new SysJob();
        }
        return sysJob;
    }

    /**
     * 获取用户部门
     *
     * @param user
     * @return
     */
    private SysDept getDept(SysUser user) {
        return sysDeptService.getOne(new LambdaQueryWrapper<SysDept>().select(SysDept::getName, SysDept::getPath).eq(SysDept::getDeptId, user.getDeptId()));
    }

    /**
     * 获取用户角色
     *
     * @param user
     * @return
     */
    private List<SysRole> getRoles(SysUser user) {
        return roleService.getRolesByUserId(user.getUserId());
    }

    /**
     * 获取权限标识
     *
     * @param user
     * @return
     */
    private List<GrantedAuthority> getGrantedAuthorities(SysUser user) {
        List<String> permissions;
        //是否超管
        if (user.getIsAdmin()) {
            //超管拥有所有权限标识符
            LambdaQueryWrapper<SysMenu> queryWrapper = new LambdaQueryWrapper<SysMenu>().select(SysMenu::getPermission).isNotNull(SysMenu::getPermission);
            permissions = sysMenuService.listObjs(queryWrapper, Object::toString);
        } else {
            //其他角色查询权限
            permissions = sysMenuService.findPermissionByUserId(user.getUserId());
        }
        String collect = String.join(",", permissions);
        return AuthorityUtils.commaSeparatedStringToAuthorityList(collect);
    }


    /**
     * 获取数据权限
     *
     * @param roles
     * @param dept
     * @return
     */
    private List<Integer> getDeptIds(List<SysRole> roles, SysDept dept) {
        boolean maxPer = roles.stream().map(SysRole::getDataScope).collect(Collectors.toSet()).contains(DataScope.Type.ALL.name());
        if (maxPer) {
            return Lists.newArrayList();
        }
        List<Integer> deptIds = Lists.newArrayList();
        for (SysRole role : roles) {
            String scope = role.getDataScope();
            if (scope.equals(DataScope.Type.CURRENT.name())) {
                //查询当前用户部门下的所有部门
                LambdaQueryWrapper<SysDept> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.select(SysDept::getDeptId).likeRight(SysDept::getPath, dept.getPath());
                List<Integer> ids = sysDeptService.listObjs(queryWrapper, Object -> Integer.parseInt(Object.toString()));
                deptIds.addAll(ids);
            }
            if (scope.equals(DataScope.Type.CUSTOM.name())) {
                //查询自定义角色部门
                LambdaQueryWrapper<SysRoleDept> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.select(SysRoleDept::getDeptId).eq(SysRoleDept::getRoleId, role.getRoleId());
                List<Integer> ids = sysRoleDeptService.listObjs(queryWrapper, Object -> Integer.parseInt(Object.toString()));
                deptIds.addAll(ids);
            }
        }
        if (deptIds.size() == 0) {
            //没有数据权限,这种情况一般是 权限选择了自定义，但是没给分配角色部门
            return null;
        }
        return deptIds;
    }
}
