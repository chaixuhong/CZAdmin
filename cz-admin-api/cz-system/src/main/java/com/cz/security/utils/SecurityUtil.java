package com.cz.security.utils;

import com.cz.security.bean.LoginUser;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @className: SecurityUtil
 * @description: security工具类
 * @author: chaizi
 * @date: 2021/12/13
 **/
public class SecurityUtil {

    public static LoginUser getLoginUser(){
        return (LoginUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static String getLoginUserName(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
