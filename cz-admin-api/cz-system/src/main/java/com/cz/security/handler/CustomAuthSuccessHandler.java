package com.cz.security.handler;

import cn.hutool.core.map.MapUtil;
import com.cz.cache.LoginUserCache;
import com.cz.constant.CommonConstant;
import com.cz.enums.ResultEnum;
import com.cz.security.bean.LoginUser;
import com.cz.utils.CodeGeneratedUtil;
import com.cz.utils.DateUtil;
import com.cz.utils.RequestParseUtil;
import com.cz.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 登录成功处理器
 *
 * @author chaizi
 * @date 2021年05月12日
 */
@Component
public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private LoginUserCache loginUserCache;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        //生成token
        String token = CodeGeneratedUtil.genMD5(new StringBuilder(authentication.getName()).append(System.currentTimeMillis()).toString());
        LoginUser loginUser = (LoginUser)authentication.getPrincipal();
        String ip = RequestParseUtil.getIp(httpServletRequest);
        String browser = RequestParseUtil.getBrowser(httpServletRequest);
        String address = RequestParseUtil.getCityInfo(ip);
        loginUser.setAddress(address);
        loginUser.setIp(ip);
        loginUser.setBrowser(browser);
        loginUser.setLoginTime(DateUtil.getCurrent());
        loginUserCache.setLoginUser(token, loginUser);
        Map result = MapUtil.builder().put("token", CommonConstant.TOKEN_PREFIX + token).put("userInfo",authentication.getPrincipal()).build();
        ResponseUtil.printOutData(httpServletResponse, HttpServletResponse.SC_OK, ResultEnum.SUCCESS, result);
    }

}
