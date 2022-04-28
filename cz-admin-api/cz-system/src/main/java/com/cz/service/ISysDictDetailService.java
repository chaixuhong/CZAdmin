package com.cz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cz.entity.SysDictDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cz.model.bo.SysDictBO;
import com.cz.model.bo.SysDictDetailBO;
import com.cz.model.query.DictDetailListQuery;
import com.github.yulichang.base.MPJBaseService;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * <p>
 * 数据字典详情 服务类
 * </p>
 *
 * @author chaizi
 * @since 2022-03-30
 */
public interface ISysDictDetailService extends MPJBaseService<SysDictDetail> {

    IPage<SysDictDetail> dictDetails(DictDetailListQuery query, Pageable pageable);


    /**
     * 保存字典
     *
     * @param sysDictBO
     */
    void saveDictDetail(SysDictDetailBO sysDictBO);

    /**
     * 更新字典
     *
     * @param sysDictBO
     */
    void updateDictDetail(SysDictDetailBO sysDictBO);

    /**
     * 批量删除字典
     *
     * @param detailId
     */
    void delDictDetail(Integer detailId);

    /**
     * 获取字典详情
     * @param dictName
     * @return
     */
    List<SysDictDetail> listByName(String dictName);
}
