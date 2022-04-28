package com.cz.service;

import com.cz.entity.SysDept;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cz.model.bo.SysDeptBO;
import com.cz.model.query.DeptListQuery;
import com.cz.model.vo.DeptVO;
import com.github.yulichang.base.MPJBaseService;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * <p>
 * 部门 服务类
 * </p>
 *
 * @author chaizi
 * @since 2021-12-09
 */
public interface ISysDeptService extends MPJBaseService<SysDept> {


    /**
     * 获取部门列表-树状
     *
     * @param deptListQuery
     * @return
     */
    List<DeptVO> deptTree(DeptListQuery deptListQuery);

    /**
     * 新建部门
     *
     * @param sysDeptBO
     */
    void saveDept(SysDeptBO sysDeptBO);

    /**
     * 更新部门
     *
     * @param sysDeptBO
     */
    void updateDept(SysDeptBO sysDeptBO);

    /**
     * 删除部门
     *
     * @param ids
     */
    void delDepts(List<Integer> ids);
}
