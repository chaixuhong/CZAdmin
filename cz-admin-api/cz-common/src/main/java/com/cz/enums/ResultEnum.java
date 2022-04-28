package com.cz.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultEnum {
    SUCCESS(1, "success"),
    NO_PERMISSION(401,"非法操作"),
    SERVER_ERROR(500,"服务器异常！"),
    /**
     * 登录异常码
     */
    USERNAME_OR_PASSWORD_ERROR(800,"用户名或密码错误"),
    ACCOUNT_IS_LOCKED(801,"账号被锁定，请联系管理员"),
    PASSWORD_IS_EXPIRED(802,"密码已过期，请联系管理员"),
    ACCOUNT_IS_EXPIRED(803,"账号已过期，请联系管理员"),
    ACCOUNT_IS_DISABLED(804,"账号被禁用，请联系管理员"),
    CODE_ERROR_INPUT(805,"验证码输入错误"),
    CODE_IS_EXPIRED(806,"验证码已过期"),
    CONTENT_TYPE_ERROR(807,"数据格式错误，需要application-json"),
    PASSWORD_TYPE_ERROR(808,"数据格式错误"),

    LOGIN_IS_EXPIRED(1000,"登录失效"),
    NO_AUTH_ACCESS(1001,"无权访问，请联系管理员"),

    PARAM_ERROR(1002, "参数错误"),
    FILE_PARAM_ERROR(1003, "文件超出规定大小"),
    FILE_TYPE_ERROR(1004, "文件类型错误"),
    FILE_UPLOAD_ERROR(1005, "文件上传错误"),


    ;
    private final int code;
    private final String errMsg;

}
