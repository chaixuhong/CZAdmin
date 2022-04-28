package com.cz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cz.entity.SysDict;
import com.cz.entity.SysDictDetail;
import com.cz.mapper.SysDictDetailMapper;
import com.cz.mapper.SysDictMapper;
import com.cz.model.bo.SysDictBO;
import com.cz.model.bo.SysDictDetailBO;
import com.cz.model.query.DictDetailListQuery;
import com.cz.security.utils.SecurityUtil;
import com.cz.service.ISysDictDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cz.utils.PageUtil;
import com.cz.utils.QueryUtil;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 数据字典详情 服务实现类
 * </p>
 *
 * @author chaizi
 * @since 2022-03-30
 */
@Service
public class SysDictDetailServiceImpl extends MPJBaseServiceImpl<SysDictDetailMapper, SysDictDetail> implements ISysDictDetailService {

    @Resource
    private SysDictMapper sysDictMapper;


    @Override
    public IPage<SysDictDetail> dictDetails(DictDetailListQuery query, Pageable pageable) {
        PageUtil<SysDictDetail> pageUtil = new PageUtil<>(pageable);
        MPJQueryWrapper<SysDictDetail> wrapper = QueryUtil.buildWrapper(query, SysDictDetail.class);
        return this.page(pageUtil, wrapper);
    }

    @Override
    public void saveDictDetail(SysDictDetailBO sysDictDetailBO) {
        SysDictDetail sysDictDetail = new SysDictDetail();
        BeanUtils.copyProperties(sysDictDetailBO, sysDictDetail);
        sysDictDetail.setCreateBy(SecurityUtil.getLoginUserName()).setCreateTime(LocalDateTime.now());
        this.save(sysDictDetail);
    }

    @Override
    public void updateDictDetail(SysDictDetailBO sysDictDetailBO) {
        SysDictDetail sysDictDetail = new SysDictDetail();
        BeanUtils.copyProperties(sysDictDetailBO, sysDictDetail);
        sysDictDetail.setUpdateBy(SecurityUtil.getLoginUserName()).setUpdateTime(LocalDateTime.now());
        this.updateById(sysDictDetail);
    }

    @Override
    public void delDictDetail(Integer detailId) {
        this.removeById(detailId);
    }

    @Override
    public List<SysDictDetail> listByName(String dictName) {
        LambdaQueryWrapper<SysDict> sdQueryWrapper = new LambdaQueryWrapper<>();
        sdQueryWrapper.eq(SysDict::getName, dictName);
        SysDict sysDict = sysDictMapper.selectOne(sdQueryWrapper);
        LambdaQueryWrapper<SysDictDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysDictDetail::getDictId, sysDict.getDictId());
        return this.list(queryWrapper);
    }
}
