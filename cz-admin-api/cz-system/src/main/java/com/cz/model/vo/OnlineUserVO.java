package com.cz.model.vo;

import com.cz.entity.SysUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class OnlineUserVO {

    @ApiModelProperty(value = "ID")
    private Long userId;

    @ApiModelProperty(value = "部门")
    private String deptName;

    @ApiModelProperty(value = "岗位")
    private String jobName;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "性别")
    private String gender;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "头像地址")
    private String avatarName;

    @ApiModelProperty(value = "是否启用")
    private Boolean enabled;

    @ApiModelProperty(value = "创建操作人")
    private String createBy;

    @ApiModelProperty(value = "更新操作人")
    private String updateBy;

    @ApiModelProperty(value = "修改密码的时间")
    private LocalDateTime pwdResetTime;

    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "当前登录时间")
    private String loginTime;

    @ApiModelProperty(value = "当前登录ip")
    private String ip;

    @ApiModelProperty(value = "当前登录地址")
    private String address;

    @ApiModelProperty(value = "当前登录终端")
    private String browser;

    public OnlineUserVO(SysUser source, String loginTime, String jobName, String deptName, String ip, String address, String browser) {
        BeanUtils.copyProperties(source, this);
        this.loginTime = loginTime;
        this.jobName = jobName;
        this.deptName = deptName;
        this.ip = ip;
        this.address = address;
        this.browser = browser;
    }
}
