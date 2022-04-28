package com.cz.security.handler;

import com.cz.enums.ResultEnum;
import com.cz.security.exception.ValidateDataException;
import com.cz.utils.ResponseUtil;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败处理handler
 *
 * @author chaizi
 * @date 2021年05月12日
 */
@Component
public class CustomAuthFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        if (e instanceof ValidateDataException) {//验证码错误或失效
            ResponseUtil.printOut(httpServletResponse, HttpServletResponse.SC_BAD_REQUEST, ((ValidateDataException) e).getResultEnum());
        } else if (e instanceof LockedException) { //账号被锁定
            ResponseUtil.printOut(httpServletResponse, HttpServletResponse.SC_BAD_REQUEST, ResultEnum.ACCOUNT_IS_LOCKED);
        } else if (e instanceof CredentialsExpiredException) { //密码已过期
            ResponseUtil.printOut(httpServletResponse, HttpServletResponse.SC_BAD_REQUEST, ResultEnum.PASSWORD_IS_EXPIRED);
        } else if (e instanceof AccountExpiredException) {//账号已过期
            ResponseUtil.printOut(httpServletResponse, HttpServletResponse.SC_BAD_REQUEST, ResultEnum.ACCOUNT_IS_EXPIRED);
        } else if (e instanceof DisabledException) {//账号已禁用
            ResponseUtil.printOut(httpServletResponse, HttpServletResponse.SC_BAD_REQUEST, ResultEnum.ACCOUNT_IS_DISABLED);
        } else {//用户名或密码错误
            ResponseUtil.printOut(httpServletResponse, HttpServletResponse.SC_BAD_REQUEST, ResultEnum.USERNAME_OR_PASSWORD_ERROR);
        }
    }
}
