package com.cz.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cz.entity.SysMenu;
import com.cz.entity.SysRolesMenus;
import com.cz.mapper.SysMenuMapper;
import com.cz.mapper.SysRolesMenusMapper;
import com.cz.model.bo.SysMenuBO;
import com.cz.model.vo.MenusVO;
import com.cz.security.utils.SecurityUtil;
import com.cz.service.ISysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cz.utils.PageUtil;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统菜单 服务实现类
 * </p>
 *
 * @author chaizi
 * @since 2021-12-07
 */
@Service
public class SysMenuServiceImpl extends MPJBaseServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Resource
    private SysMenuMapper sysMenuMapper;

    @Resource
    private SysRolesMenusMapper sysRolesMenusMapper;

    @Override
    public List<String> findPermissionByUserId(Long userId) {
        return sysMenuMapper.findPermissionByUserId(userId);
    }


    @Override
    public List<MenusVO> getMenusTree() {
        List<SysMenu> sysMenus;
        if (SecurityUtil.getLoginUser().getUser().getIsAdmin()) {
            sysMenus = this.list(new LambdaQueryWrapper<SysMenu>().lt(SysMenu::getMenuType, 2).orderByAsc(SysMenu::getMenuSort));
        } else {
            sysMenus = sysMenuMapper.getMenusByUserId(SecurityUtil.getLoginUser().getUser().getUserId());
        }
        List<MenusVO> source = JSONObject.parseArray(JSONObject.toJSONString(sysMenus), MenusVO.class);
        List<MenusVO> target = Lists.newArrayList();
        buildParentMenus(source, target);
        buildMenus(source, target);
        /**
         * 修复只授权子菜单无法获取父菜单bug
         */
        if (!source.isEmpty()) {
            List<Integer> pids = source.stream().map(MenusVO::getPid).distinct().collect(Collectors.toList());
            List<SysMenu> menus = sysMenuMapper.selectList(new LambdaQueryWrapper<SysMenu>().in(SysMenu::getMenuId, pids));
            source.addAll(JSONObject.parseArray(JSONObject.toJSONString(menus), MenusVO.class));
            buildParentMenus(source, target);
            buildMenus(source, target);
            target = target.stream().sorted(Comparator.comparing(MenusVO::getMenuSort)).collect(Collectors.toList());
        }
        return target;
    }

    /**
     * 找出父级菜单
     *
     * @param source
     * @param target
     */
    private void buildParentMenus(List<MenusVO> source, List<MenusVO> target) {
        for (int i = 0; i < source.size(); i++) {
            //根节点pid=0
            if (source.get(i).getPid() == 0) {
                //root
                target.add(source.get(i));
                source.remove(i);
                i--;
            }
        }
    }

    /**
     * 找出父级菜单的子菜单
     *
     * @param source
     * @param target
     */
    private void buildMenus(List<MenusVO> source, List<MenusVO> target) {
        for (int i = 0; i < target.size(); i++) {
            MenusVO t = target.get(i);
            for (int j = 0; j < source.size(); j++) {
                MenusVO s = source.get(j);
                if (s.getPid().equals(t.getMenuId())) {
                    t.getChildren().add(s);
                    source.remove(j);
                    j--;
                }
            }
        }
        if (source.size() > 0) {
            for (MenusVO t : target) {
                if (t.getChildren().size() > 0) {
                    buildMenus(source, t.getChildren());
                }
            }
        }
    }

    @Override
    public List<SysMenu> getMenus(Integer pid) {
        LambdaQueryWrapper<SysMenu> queryWrapper = new LambdaQueryWrapper<>();
        if (Objects.isNull(pid)) {
            pid = 0;
        }
        queryWrapper.orderByAsc(SysMenu::getMenuSort);
        return this.list(queryWrapper.eq(SysMenu::getPid, pid));
    }


    @Override
    public void saveMenu(SysMenuBO sysMenuBO) {
        if (!sysMenuBO.getPid().equals(0)) {
            SysMenu parent = new SysMenu();
            parent.setMenuId(sysMenuBO.getPid()).setHasChildren(true);
            this.updateById(parent);
        }
        SysMenu sysMenu = new SysMenu(sysMenuBO);
        sysMenu.setHasChildren(false);
        this.save(sysMenu);
    }

    @Transactional
    @Override
    public List<SysMenu> delMenu(List<Integer> ids) {
        List<SysMenu> menus = Lists.newArrayList();//保存被删除的菜单到日志
        this.dealHasChildren(ids);
        this.deepDel(ids, menus);
        /**
         * 删除菜单角色关系
         */
        List<Integer> collect = menus.stream().map(SysMenu::getMenuId).collect(Collectors.toList());
        sysRolesMenusMapper.delete(new LambdaQueryWrapper<SysRolesMenus>().in(SysRolesMenus::getMenuId, collect));
        return menus;
    }

    /**
     * 递归删除所有子菜单
     *
     * @param ids
     */
    private void deepDel(List<Integer> ids, List<SysMenu> menus) {
        for (int i = 0; i < ids.size(); i++) {
            SysMenu sysMenu = this.getById(ids.get(i));
            if (Objects.isNull(sysMenu)) continue;
            menus.add(sysMenu);
            if (!Objects.isNull(sysMenu)) {
                this.removeById(sysMenu.getMenuId());
                LambdaQueryWrapper<SysMenu> queryWrapper = new LambdaQueryWrapper<>();
                List<Integer> idList = this.list(queryWrapper.eq(SysMenu::getPid, ids.get(i)).select(SysMenu::getMenuId)).stream().map(SysMenu::getMenuId).collect(Collectors.toList());
                if (idList.size() > 0) {
                    deepDel(idList, menus);
                }
            }
        }
    }

    /**
     * 重置父级hasChildren标志位
     *
     * @param ids
     */
    private void dealHasChildren(List<Integer> ids) {
        for (Integer id : ids) {
            SysMenu sysMenu = this.getById(id);
            int count = this.count(new LambdaQueryWrapper<SysMenu>().eq(SysMenu::getPid, sysMenu.getPid()));
            if (count == 1 && sysMenu.getPid() != 0) {
                SysMenu update = new SysMenu();
                update.setMenuId(sysMenu.getPid()).setHasChildren(false);
                this.updateById(update);
            }
        }
    }


    @Override
    public List<Integer> getChildIds(Integer id) {
        List<Integer> ids = Lists.newArrayList(id);
        List<Integer> child = Lists.newArrayList(id);
        deepIds(ids, child);
        return ids;
    }

    /**
     * 找出菜单的所有子菜单id
     *
     * @param ids
     * @param child
     */
    private void deepIds(List<Integer> ids, List<Integer> child) {
        for (int i = 0; i < child.size(); i++) {
            LambdaQueryWrapper<SysMenu> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(SysMenu::getPid, child.get(i)).select(SysMenu::getMenuId);
            List<Integer> collect = this.list(queryWrapper).stream().map(SysMenu::getMenuId).collect(Collectors.toList());
            if (collect.size() > 0) {
                ids.addAll(collect);
                deepIds(ids, collect);
            }
        }
    }
}

