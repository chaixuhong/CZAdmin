package com.cz.mapper;

import com.cz.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 系统菜单 Mapper 接口
 * </p>
 *
 * @author chaizi
 * @since 2021-12-07
 */
@Mapper
public interface SysMenuMapper extends MPJBaseMapper<SysMenu> {

    List<String> findPermissionByUserId(@Param("userId") Long userId);

    List<SysMenu> getMenusByUserId(Long userId);
}
