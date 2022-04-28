package com.cz.mapper;

import com.cz.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author chaizi
 * @since 2021-12-07
 */
@Mapper
public interface SysRoleMapper extends MPJBaseMapper<SysRole> {

    List<SysRole> getRolesByUserId(Long userId);
}
