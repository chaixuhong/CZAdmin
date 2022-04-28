package com.cz.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.NoArgsConstructor;
import com.cz.model.bo.SysEmailConfigBO;
import org.springframework.beans.BeanUtils;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

/**
* @description sys_email_config实体类-邮箱配置
* @author chaizi
* @date 2022-04-25
**/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_email_config")
@ApiModel(value = "sys_email_config实体类", description = "邮箱配置")
public class SysEmailConfig implements Serializable {

    private static final long serialVersionUID = 1650875728893870L;

    @TableId(value = "email_id", type = IdType.AUTO)
    @ApiModelProperty(value = "ID")
    private Integer emailId;

    @ApiModelProperty(value = "发件人")
    private String fromUser;

    @ApiModelProperty(value = "邮件服务器SMTP地址")
    private String host;

    @ApiModelProperty(value = "密码")
    @JSONField(serialize = false)
    private String pass;

    @ApiModelProperty(value = "端口")
    private Integer port;

    @ApiModelProperty(value = "发件人用户名")
    private String user;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建人")
    private String createBy;

    @TableField(fill = FieldFill.UPDATE)
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.UPDATE)
    @ApiModelProperty(value = "更新人")
    private String updateBy;

    public SysEmailConfig(SysEmailConfigBO bo){
        BeanUtils.copyProperties(bo, this);
    }



}