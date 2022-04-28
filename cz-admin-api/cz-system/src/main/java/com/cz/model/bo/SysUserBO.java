package com.cz.model.bo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@ApiModel("用户接收类")
@Data
public class SysUserBO {

    @ApiModelProperty(value = "ID")
    private Long userId;

    @ApiModelProperty(value = "部门id")
    @NotNull(message = "部门不能为空")
    private Integer deptId;

    @ApiModelProperty(value = "岗位id")
    private Integer jobId;

    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(value = "昵称")
    @NotBlank(message = "昵称不能为空")
    private String nickName;

    @ApiModelProperty(value = "性别")
    @NotBlank(message = "性别不能为空")
    private String gender;

    @ApiModelProperty(value = "手机号码")
    @NotBlank(message = "手机号码不能为空")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    @NotBlank(message = "邮箱不能为空")
    private String email;

    @ApiModelProperty(value = "状态")
    @NotNull(message = "状态不能为空")
    private Boolean enabled;

    @ApiModelProperty(value = "角色ids")
    private List<Integer> roles;

}
