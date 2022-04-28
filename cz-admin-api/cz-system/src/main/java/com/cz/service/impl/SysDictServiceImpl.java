package com.cz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cz.entity.SysDict;
import com.cz.entity.SysDictDetail;
import com.cz.mapper.SysDictDetailMapper;
import com.cz.mapper.SysDictMapper;
import com.cz.model.bo.SysDictBO;
import com.cz.model.query.DictListQuery;
import com.cz.security.utils.SecurityUtil;
import com.cz.service.ISysDictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cz.utils.PageUtil;
import com.cz.utils.QueryUtil;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 数据字典 服务实现类
 * </p>
 *
 * @author chaizi
 * @since 2022-03-24
 */
@Service
public class SysDictServiceImpl extends MPJBaseServiceImpl<SysDictMapper, SysDict> implements ISysDictService {

    @Resource
    private SysDictDetailMapper sysDictDetailMapper;

    @Override
    public void saveDict(SysDictBO sysDictBO) {
        SysDict sysDict = new SysDict();
        BeanUtils.copyProperties(sysDictBO, sysDict);
        sysDict.setCreateBy(SecurityUtil.getLoginUserName()).setCreateTime(LocalDateTime.now());
        this.save(sysDict);
    }

    @Override
    public void updateDict(SysDictBO sysDictBO) {
        SysDict sysDict = new SysDict();
        BeanUtils.copyProperties(sysDictBO, sysDict);
        sysDict.setUpdateBy(SecurityUtil.getLoginUserName()).setUpdateTime(LocalDateTime.now());
        this.updateById(sysDict);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delDicts(List<Integer> ids) {
        this.removeByIds(ids);
        for (Integer dictId : ids) {
            LambdaQueryWrapper<SysDictDetail> queryWrapper = new LambdaQueryWrapper<>();
            sysDictDetailMapper.delete(queryWrapper.eq(SysDictDetail::getDictId, dictId));
        }
    }

    @Override
    public IPage<SysDict> dicts(DictListQuery dictListQuery, Pageable pageable) {
        PageUtil<SysDict> pageUtil = new PageUtil<>(pageable);
        MPJQueryWrapper<SysDict> wrapper = QueryUtil.buildWrapper(dictListQuery,SysDict.class);
        return this.page(pageUtil, wrapper);
    }
}
