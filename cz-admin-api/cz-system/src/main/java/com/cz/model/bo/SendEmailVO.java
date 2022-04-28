package com.cz.model.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "sys_email_config操作类", description = "邮箱配置")
public class SendEmailVO implements Serializable {

    private static final long serialVersionUID = 7784705232900520700L;
    @NotEmpty(message = "收件人不能为空")
    @ApiModelProperty(value = "收件人，支持多个收件人")
    private List<String> tos;
    @NotEmpty(message = "标题不能为空")
    @ApiModelProperty(value = "标题")
    private String title;
    @NotEmpty(message = "内容id不能为空")
    @ApiModelProperty(value = "内容")
    private String content;
    @NotNull(message = "配置id不能为空")
    @ApiModelProperty(value = "配置id")
    private Integer emailId;
}
