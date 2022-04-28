package com.cz.mapper;

import com.cz.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 系统用户 Mapper 接口
 * </p>
 *
 * @author chaizi
 * @since 2021-12-06
 */
@Mapper
public interface SysUserMapper extends MPJBaseMapper<SysUser> {

}
