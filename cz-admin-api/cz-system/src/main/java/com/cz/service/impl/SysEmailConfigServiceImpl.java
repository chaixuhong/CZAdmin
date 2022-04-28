package com.cz.service.impl;

import cn.hutool.core.lang.Dict;
import cn.hutool.extra.mail.Mail;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import com.cz.constant.CommonConstant;
import com.cz.entity.SysEmailConfig;
import com.cz.model.bo.SendEmailVO;
import com.cz.service.ISysEmailConfigService;
import com.cz.model.bo.SysEmailConfigBO;
import com.cz.model.query.SysEmailConfigQuery;
import com.cz.utils.RsaUtils;
import org.springframework.stereotype.Service;
import com.cz.mapper.SysEmailConfigMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.cz.utils.PageUtil;
import com.cz.utils.QueryUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cz.exception.BusinessException;

/**
 * @author chaizi
 * @description 服务实现
 * @date 2022-04-25
 **/
@Service
public class SysEmailConfigServiceImpl extends MPJBaseServiceImpl<SysEmailConfigMapper, SysEmailConfig> implements ISysEmailConfigService {

    @Override
    public IPage<SysEmailConfig> queryAll(SysEmailConfigQuery query, Pageable pageable) {
        PageUtil<SysEmailConfig> pageUtil = new PageUtil<>(pageable);
        MPJQueryWrapper<SysEmailConfig> wrapper = QueryUtil.buildWrapper(query, SysEmailConfig.class);
        return this.page(pageUtil, wrapper);
    }

    @Override
    public List<SysEmailConfig> queryAll(SysEmailConfigQuery query) {
        MPJQueryWrapper<SysEmailConfig> wrapper = QueryUtil.buildWrapper(query, SysEmailConfig.class);
        return this.list(wrapper);
    }

    @Override
    @Transactional
    public SysEmailConfig findById(Integer emailId) {
        return this.getById(emailId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SysEmailConfig create(SysEmailConfigBO resources) throws Exception {
        SysEmailConfig bean = new SysEmailConfig(resources);
        this.save(bean);
        return bean;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysEmailConfigBO resources) {
        if (Objects.isNull(resources.getEmailId())) {
            throw new BusinessException("缺少id，非法操作数据");
        }
        SysEmailConfig bean = new SysEmailConfig(resources);
        this.updateById(bean);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<SysEmailConfig> deleteAll(List<Integer> ids) {
        List<SysEmailConfig> sysEmailConfigs = this.listByIds(ids);
        Set<Integer> collect = sysEmailConfigs.stream().map(SysEmailConfig::getEmailId).collect(Collectors.toSet());
        this.removeByIds(collect);
        return sysEmailConfigs;
    }

    @Override
    public void send(SendEmailVO sendEmailVO) {
        SysEmailConfig emailConfig = this.getById(sendEmailVO.getEmailId());
        if (Objects.isNull(emailConfig)) {
            throw new BusinessException("请先配置，再操作");
        }

        TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig("template", TemplateConfig.ResourceMode.CLASSPATH));
        Template template = engine.getTemplate("email/sendEmail.ftl");

        // 封装
        MailAccount account = new MailAccount();
        // 设置用户
        String user = emailConfig.getFromUser().split("@")[0];
        if (emailConfig.getHost().contains("exmail"))
            account.setUser(emailConfig.getFromUser());//腾讯企业邮箱特殊处理，因为大部分企业自定义域名
        else account.setUser(user);
        account.setHost(emailConfig.getHost());
        account.setPort(emailConfig.getPort());
        account.setAuth(true);
        try {
            account.setPass(RsaUtils.decryptByPrivateKey(CommonConstant.RAS_PRIVATE_KEY, emailConfig.getPass()));
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("发送邮件：密码解密失败");
        }
        account.setFrom(emailConfig.getUser() + "<" + emailConfig.getFromUser() + ">");
        // ssl方式发送
        account.setSslEnable(true);
        // 使用STARTTLS安全连接
        account.setStarttlsEnable(true);
        String content = template.render(Dict.create().set("content",sendEmailVO.getContent()));
        // 发送
        try {
            int size = sendEmailVO.getTos().size();
            Mail.create(account)
                    .setTos(sendEmailVO.getTos().toArray(new String[size]))
                    .setTitle(sendEmailVO.getTitle())
                    .setContent(content)
                    .setHtml(true)
                    //关闭session
                    .setUseGlobalSession(false)
                    .send();
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

}