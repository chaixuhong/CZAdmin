package com.cz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cz.entity.SysDept;
import com.cz.entity.SysMenu;
import com.cz.entity.SysUser;
import com.cz.exception.BusinessException;
import com.cz.mapper.SysDeptMapper;
import com.cz.mapper.SysUserMapper;
import com.cz.model.bo.SysDeptBO;
import com.cz.model.query.DeptListQuery;
import com.cz.model.vo.DeptVO;
import com.cz.security.utils.SecurityUtil;
import com.cz.service.ISysDeptService;
import com.cz.service.ISysUserService;
import com.cz.utils.QueryUtil;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.google.common.collect.Lists;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 部门 服务实现类
 * </p>
 *
 * @author chaizi
 * @since 2021-12-09
 */
@Service
public class SysDeptServiceImpl extends MPJBaseServiceImpl<SysDeptMapper, SysDept> implements ISysDeptService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public List<DeptVO> deptTree(DeptListQuery deptListQuery) {
        MPJQueryWrapper<SysDept> queryWrapper = QueryUtil.buildWrapper(deptListQuery,SysDept.class);
        queryWrapper.orderByAsc("t.dept_sort");
        //查询权限范围内的部门列表
        List<Integer> dataScope = SecurityUtil.getLoginUser().getDataScope();
        if (dataScope == null) {
            return Lists.newArrayList();
        } else if (dataScope.size() > 0) {
            queryWrapper.in("dept_id", dataScope);
        }
        List<SysDept> sysDeptList = this.list(queryWrapper);
        //找出组织根节点，进行树状组装
        int rootLevel = this.deptMinLevel(sysDeptList);
        List<DeptVO> root = Lists.newArrayList();
        for (int i = 0; i < sysDeptList.size(); i++) {
            SysDept item = sysDeptList.get(i);
            int level = this.deptLevel(item);
            if (rootLevel == level) {
                DeptVO deptVO = new DeptVO();
                BeanUtils.copyProperties(item, deptVO);
                root.add(deptVO);
                sysDeptList.remove(item);
                i--;
            }
        }
        this.buildTree(root, sysDeptList);
        return root;
    }

    /**
     * 获取部门列表的最级
     *
     * @param list 部门列表
     * @return
     */
    private int deptMinLevel(List<SysDept> list) {
        int min = 999;
        for (SysDept item : list) {
            int level = this.deptLevel(item);
            if (level == 1) return level;
            if (level < min) min = level;
        }
        return min;
    }

    /**
     * 获取部门级别
     *
     * @param sysDept 部门
     * @return
     */
    private int deptLevel(SysDept sysDept) {
        if (Strings.isBlank(sysDept.getPath())) {
            log.error("部门路径为空！");
            return 999;
        }
        return sysDept.getPath().split("_").length;
    }

    /**
     * 构建树结构
     *
     * @param root  根节点
     * @param child 子节点
     */
    private void buildTree(List<DeptVO> root, List<SysDept> child) {
        for (int i = 0; i < root.size(); i++) {
            DeptVO rootItem = root.get(i);
            List<DeptVO> rootChild = Lists.newArrayList();
            for (int j = 0; j < child.size(); j++) {
                SysDept childItem = child.get(j);
                if (childItem.getPid() == rootItem.getDeptId()) {
                    DeptVO deptVO = new DeptVO();
                    BeanUtils.copyProperties(childItem, deptVO);
                    rootChild.add(deptVO);
                    child.remove(childItem);
                    j--;
                }
            }
            if (rootChild.size() > 0) {
                buildTree(rootChild, child);
                rootItem.setChildren(rootChild);
            }
        }
    }

    @Override
    public void saveDept(SysDeptBO sysDeptBO) {
        SysDept sysDept = new SysDept();
        BeanUtils.copyProperties(sysDeptBO, sysDept);
        sysDept.setCreateTime(LocalDateTime.now()).setCreateBy(SecurityUtil.getLoginUserName());
        if (Objects.isNull(sysDept.getPid())) {
            sysDept.setPid(0).setPath(sysDept.getName());
        } else {
            //查询父级路径
            SysDept parentDept = this.getOne(new LambdaQueryWrapper<SysDept>().eq(SysDept::getDeptId, sysDept.getPid()));
            String currentPath = parentDept.getPath() + "_" + sysDept.getName();
            sysDept.setPath(currentPath);
        }
        this.save(sysDept);
    }

    @Override
    public void updateDept(SysDeptBO sysDeptBO) {
        SysDept sysDept = new SysDept();
        BeanUtils.copyProperties(sysDeptBO, sysDept);
        sysDept.setUpdateTime(LocalDateTime.now()).setUpdateBy(SecurityUtil.getLoginUserName());
        if (Objects.isNull(sysDept.getPid())) {
            sysDept.setPid(0).setPath(sysDept.getName());
        } else {
            //查询父级路径
            SysDept parentDept = this.getOne(new LambdaQueryWrapper<SysDept>().eq(SysDept::getDeptId, sysDept.getPid()));
            String currentPath = parentDept.getPath() + "_" + sysDept.getName();
            sysDept.setPath(currentPath);
        }
        this.updateById(sysDept);
    }

    @Override
    public void delDepts(List<Integer> ids) {
        SysDept sysDept = this.getOne(new LambdaQueryWrapper<SysDept>().select(SysDept::getPath).eq(SysDept::getDeptId,ids.get(0)));//为了兼容前端通用组件，其实只能删除一个
        List<SysDept> list = this.list(new LambdaQueryWrapper<SysDept>().likeRight(SysDept::getPath, sysDept.getPath()));
        List<Integer> collect = list.stream().map(SysDept::getDeptId).collect(Collectors.toList());
        Integer selectCount = sysUserMapper.selectCount(new LambdaQueryWrapper<SysUser>().in(SysUser::getDeptId,collect ));
        if (selectCount > 0) {
            throw new BusinessException("不能删除有用户的部门");
        }
        this.removeByIds(collect);

    }


}
