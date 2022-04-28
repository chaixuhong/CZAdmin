package com.cz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cz.entity.SysJob;
import com.cz.entity.SysUser;
import com.cz.mapper.SysJobMapper;
import com.cz.model.bo.SysJobBO;
import com.cz.model.query.JobListQuery;
import com.cz.security.utils.SecurityUtil;
import com.cz.service.ISysJobService;
import com.cz.utils.PageUtil;
import com.cz.utils.QueryUtil;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 岗位 服务实现类
 * </p>
 *
 * @author chaizi
 * @since 2022-03-23
 */
@Service
public class SysJobServiceImpl extends MPJBaseServiceImpl<SysJobMapper, SysJob> implements ISysJobService {

    @Override
    public void saveJob(SysJobBO sysJobBO) {
        SysJob sysJob = new SysJob();
        BeanUtils.copyProperties(sysJobBO,sysJob);
        sysJob.setCreateBy(SecurityUtil.getLoginUserName()).setCreateTime(LocalDateTime.now());
        this.save(sysJob);
    }

    @Override
    public void updateJob(SysJobBO sysJobBO) {
        SysJob sysJob = new SysJob();
        BeanUtils.copyProperties(sysJobBO,sysJob);
        sysJob.setUpdateBy(SecurityUtil.getLoginUserName()).setUpdateTime(LocalDateTime.now());
        this.updateById(sysJob);
    }

    @Override
    public void delJobs(List<Integer> ids) {
        this.removeByIds(ids);
    }

    @Override
    public IPage<SysJob> jobs(JobListQuery jobListQuery, Pageable pageable) {
        PageUtil<SysJob> pageUtil = new PageUtil<>(pageable);
        MPJQueryWrapper<SysJob> wrapper = QueryUtil.buildWrapper(jobListQuery,SysJob.class);
        return this.page(pageUtil,wrapper);
    }

    @Override
    public List<SysJob> allJobs() {
        return this.list();
    }
}
