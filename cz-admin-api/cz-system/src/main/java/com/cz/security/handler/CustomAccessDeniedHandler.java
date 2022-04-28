package com.cz.security.handler;

import com.cz.enums.ResultEnum;
import com.cz.utils.ResponseUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 权限不足处理器
 *
 * @author chaizi
 * @date 2021年05月12日
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        ResponseUtil.printOut(httpServletResponse, HttpServletResponse.SC_FORBIDDEN, ResultEnum.NO_AUTH_ACCESS);

    }
}
