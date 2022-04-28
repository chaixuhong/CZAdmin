package com.cz.service;

import com.cz.entity.SysEmailConfig;
import com.cz.model.bo.SendEmailVO;
import com.cz.model.bo.SysEmailConfigBO;
import com.cz.model.query.SysEmailConfigQuery;
import org.springframework.data.domain.Pageable;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseService;

/**
 * @author chaizi
 * @description 服务接口
 * @date 2022-04-25
 **/
public interface ISysEmailConfigService extends MPJBaseService<SysEmailConfig> {

    /**
     * 查询数据分页
     *
     * @param query    条件
     * @param pageable 分页参数
     * @return List<SysEmailConfig
     */
    IPage<SysEmailConfig> queryAll(SysEmailConfigQuery query, Pageable pageable);

    /**
     * 查询所有数据不分页
     *
     * @param query 条件参数
     * @return List<SysEmailConfig>
     */
    List<SysEmailConfig> queryAll(SysEmailConfigQuery query);

    /**
     * 根据ID查询
     *
     * @param emailId ID
     * @return SysEmailConfig
     */
    SysEmailConfig findById(Integer emailId);

    /**
     * 创建
     *
     * @param resources /
     * @return SysEmailConfig
     */
    SysEmailConfig create(SysEmailConfigBO resources) throws Exception;

    /**
     * 编辑
     *
     * @param resources /
     */
    void update(SysEmailConfigBO resources);

    /**
     * 多选删除
     *
     * @param ids /
     */
    List<SysEmailConfig> deleteAll(List<Integer> ids);

    /**
     * 发送邮件
     *
     * @param sendEmailVO
     */
    void send(SendEmailVO sendEmailVO);
}