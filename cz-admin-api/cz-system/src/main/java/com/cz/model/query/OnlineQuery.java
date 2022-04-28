package com.cz.model.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@ApiModel("在线用户操作类")
@Data
public class OnlineQuery {

    @ApiModelProperty(value = "用户id")
    @NotNull(message = "用户id不能为空")
    private Long userId;

    @ApiModelProperty(value = "登录时间")
    @NotNull(message = "登录时间不能为空")
    private String loginTime;
}
