package com.cz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cz.entity.SysRole;
import com.cz.model.bo.SysRoleBO;
import com.cz.model.query.RoleListQuery;
import com.github.yulichang.base.MPJBaseService;
import org.springframework.data.domain.Pageable;


import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author chaizi
 * @since 2021-12-07
 */
public interface ISysRoleService extends MPJBaseService<SysRole> {

    /**
     * 查询列表
     * @param roleListQuery
     * @param pageable
     * @return
     */
    IPage<SysRole> list(RoleListQuery roleListQuery, Pageable pageable);


    /**
     * 新增角色
     * @param sysRoleBO
     */
    void add(SysRoleBO sysRoleBO);



    int getLevels(Integer level);


    List<SysRole> getRolesByUserId(Long userId);

    void edit(SysRoleBO sysRoleBO);

    void delete(List<Integer> ids);
}
