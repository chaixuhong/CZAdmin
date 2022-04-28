package com.cz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cz.entity.SysDict;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cz.entity.SysJob;
import com.cz.model.bo.SysDictBO;
import com.cz.model.query.DictListQuery;
import com.github.yulichang.base.MPJBaseService;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * <p>
 * 数据字典 服务类
 * </p>
 *
 * @author chaizi
 * @since 2022-03-24
 */
public interface ISysDictService extends MPJBaseService<SysDict> {

    /**
     * 保存字典
     *
     * @param sysDictBO
     */
    void saveDict(SysDictBO sysDictBO);

    /**
     * 更新字典
     *
     * @param sysDictBO
     */
    void updateDict(SysDictBO sysDictBO);

    /**
     * 批量删除字典
     *
     * @param ids
     */
    void delDicts(List<Integer> ids);

    /**
     * 获取字典列表
     *
     * @param dictListQuery
     * @param pageable
     * @return
     */
    IPage<SysDict> dicts(DictListQuery dictListQuery, Pageable pageable);

}
