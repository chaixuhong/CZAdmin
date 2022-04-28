package com.cz.security.handler;

import com.cz.cache.LoginUserCache;
import com.cz.enums.ResultEnum;
import com.cz.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登出成功处理器
 *
 * @author chaizi
 * @date 2021年05月12日
 */
@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
    @Autowired
    private LoginUserCache loginUserCache;
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        String authorization = httpServletRequest.getHeader("Authorization");
        String authToken = authorization.substring("Bearer ".length());
        loginUserCache.clearUser(authToken);//登出清理token
        ResponseUtil.printOut(httpServletResponse, HttpServletResponse.SC_OK, ResultEnum.SUCCESS);
    }
}
