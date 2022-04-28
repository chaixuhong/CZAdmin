package com.cz.security.filter;

import com.alibaba.fastjson.JSONObject;
import com.cz.cache.CodeCache;
import com.cz.constant.CommonConstant;
import com.cz.enums.ResultEnum;
import com.cz.security.exception.ValidateDataException;
import com.cz.security.handler.CustomAuthFailureHandler;
import com.cz.utils.RsaUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码登录验证过滤器
 *
 * @author chaizi
 * @date 2021年05月12日
 **/
@Slf4j
public class CodeAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private CustomAuthFailureHandler customAuthFailureHandler;

    @Autowired
    private CodeCache codeCache;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)
            throws ServletException, IOException {

        if (StringUtils.equals("/api/login", httpServletRequest.getRequestURI())
                && StringUtils.equalsIgnoreCase(httpServletRequest.getMethod(), "post")) {
            try {
                validateData(httpServletRequest);
            } catch (ValidateDataException e) {
                customAuthFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
                return;
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }


    /**
     * 校验验证码
     *
     * @param request
     */
    private void validateData(HttpServletRequest request) {
        //校验数据格式，只支持json方式提交
        if (Strings.isBlank(request.getContentType()) || !request.getContentType().contains(MediaType.APPLICATION_JSON_VALUE)) {
            throw new ValidateDataException(ResultEnum.CONTENT_TYPE_ERROR);
        }
        JSONObject requestParams = null;
        try {
            requestParams = new ObjectMapper().readValue(request.getInputStream(), JSONObject.class);
        } catch (IOException e) {
            throw new ValidateDataException(ResultEnum.SERVER_ERROR);
        }

        String code = requestParams.getString("code");
        String uuid = requestParams.getString("uuid");
        String username = requestParams.getString("username");
        String password = requestParams.getString("password");
        String rememberMe = requestParams.getString("rememberMe");

        if (StringUtils.isBlank(code)) {
            log.error("提交空验证码！");
            throw new ValidateDataException(ResultEnum.CODE_ERROR_INPUT);
        }

        if (StringUtils.isBlank(uuid)) {
            log.error("提交空的uuid！");
            throw new ValidateDataException(ResultEnum.CODE_ERROR_INPUT);
        }

        if (StringUtils.isBlank(username)) {
            log.error("提交空用户名！");
            throw new ValidateDataException(ResultEnum.USERNAME_OR_PASSWORD_ERROR);
        }

        if (StringUtils.isBlank(password)) {
            log.error("提交空密码！");
            throw new ValidateDataException(ResultEnum.USERNAME_OR_PASSWORD_ERROR);
        }

        String redisCode = codeCache.getCode(uuid);

        if (redisCode == null) {
            throw new ValidateDataException(ResultEnum.CODE_IS_EXPIRED);
        }

        if (!StringUtils.equals(redisCode, code)) {
            throw new ValidateDataException(ResultEnum.CODE_ERROR_INPUT);
        }

        codeCache.clearCode(uuid);

        //前后端密码加密传输，前端使用公钥加密，后端使用私钥解密
        try {
            password = RsaUtils.decryptByPrivateKey(CommonConstant.RAS_PRIVATE_KEY, password);
        } catch (Exception e) {
            throw new ValidateDataException(ResultEnum.PASSWORD_TYPE_ERROR);
        }

        //由于流读取一次无法重复读，所以防止到请求中，提供后续过滤器处理
        request.setAttribute("username", username);
        request.setAttribute("password", password);
        request.setAttribute("rememberMe", rememberMe);


    }

}
