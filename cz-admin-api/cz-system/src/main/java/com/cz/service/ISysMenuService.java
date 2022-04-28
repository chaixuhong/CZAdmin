package com.cz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cz.entity.SysMenu;
import com.cz.model.bo.SysMenuBO;
import com.cz.model.vo.MenusVO;
import com.github.yulichang.base.MPJBaseService;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * <p>
 * 系统菜单 服务类
 * </p>
 *
 * @author chaizi
 * @since 2021-12-07
 */
public interface ISysMenuService extends MPJBaseService<SysMenu> {
    /**
     * 获取用户权限标识
     *
     * @param userId
     * @return
     */
    List<String> findPermissionByUserId(Long userId);

    /**
     * 获取用户菜单
     *
     * @return
     */
    List<MenusVO> getMenusTree();

    /**
     * 获取菜单列表
     *
     * @param pid
     * @return
     */
    List<SysMenu> getMenus(Integer pid);

    /**
     * 保存菜单
     *
     * @param sysMenuBO
     */
    void saveMenu(SysMenuBO sysMenuBO);

    /**
     * 删除菜单
     *
     * @param ids
     */
    List<SysMenu>  delMenu(List<Integer> ids);

    /**
     * 获取子级id
     *
     * @param id
     * @return
     */
    List<Integer> getChildIds(Integer id);
}
