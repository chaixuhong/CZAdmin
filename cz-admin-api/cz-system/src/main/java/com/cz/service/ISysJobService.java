package com.cz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cz.entity.SysJob;
import com.cz.model.bo.SysJobBO;
import com.cz.model.query.JobListQuery;
import com.github.yulichang.base.MPJBaseService;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * <p>
 * 岗位 服务类
 * </p>
 *
 * @author chaizi
 * @since 2022-03-23
 */
public interface ISysJobService extends MPJBaseService<SysJob> {
    /**
     * 保存岗位
     *
     * @param sysJobBO
     */
    void saveJob(SysJobBO sysJobBO);

    /**
     * 更新岗位
     *
     * @param sysJobBO
     */
    void updateJob(SysJobBO sysJobBO);

    /**
     * 批量删除岗位
     *
     * @param ids
     */
    void delJobs(List<Integer> ids);

    /**
     * 获取岗位列表
     *
     * @param jobListQuery
     * @param pageable
     * @return
     */
    IPage<SysJob> jobs(JobListQuery jobListQuery, Pageable pageable);

    /**
     * 获取所有岗位
     *
     * @return
     */
    List<SysJob> allJobs();
}
