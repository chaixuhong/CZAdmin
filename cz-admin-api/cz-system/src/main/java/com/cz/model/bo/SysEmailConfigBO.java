package com.cz.model.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
/**
* @description sys_email_config操作类-邮箱配置
* @author chaizi
* @date 2022-04-25
**/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "sys_email_config操作类", description = "邮箱配置")
public class SysEmailConfigBO implements Serializable {

    private static final long serialVersionUID = 1650875728893741L;

    @ApiModelProperty(value = "ID")
    private Integer emailId;
    @NotBlank(message = "发件人不能为空")
    @ApiModelProperty(value = "发件人")
    private String fromUser;
    @NotBlank(message = "邮件服务器SMTP地址不能为空")
    @ApiModelProperty(value = "邮件服务器SMTP地址")
    private String host;
    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码")
    private String pass;
    @NotNull(message = "端口不能为空")
    @ApiModelProperty(value = "端口")
    private Integer port;
    @NotBlank(message = "发件人用户名不能为空")
    @ApiModelProperty(value = "发件人用户名")
    private String user;
}