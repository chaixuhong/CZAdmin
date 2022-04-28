package com.cz.security.handler;

import com.cz.enums.ResultEnum;
import com.cz.utils.ResponseUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失效或者登录过期处理器
 * 其实是请求的入口，但是这里作用当handler处理了
 *
 * @author chaizi
 * @date 2021年05月12日
 */
@Component
public class CustomAuthExpiredHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ResponseUtil.printOut(httpServletResponse, HttpServletResponse.SC_UNAUTHORIZED, ResultEnum.LOGIN_IS_EXPIRED);
    }
}
