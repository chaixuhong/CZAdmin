package com.cz.cache;

import com.alibaba.fastjson.JSONObject;
import com.cz.annotation.Logger;
import com.cz.constant.CommonConstant;
import com.cz.security.bean.LoginUser;
import com.cz.utils.RedisUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * @className: LoginUserCache
 * @description: 登录用户信息缓存
 * @author: chaizi
 * @date: 2021/12/12
 **/
@Component
public class LoginUserCache {

    @Autowired
    private RedisUtil redisUtil;

    public static final String PREFIX = CommonConstant.REDIS_PREFIX + ":login:user:";

    private final long EXPIRED = 2L;

    private String genKey(String key) {
        return PREFIX + key;
    }

    @Logger("用户登录")
    public void setLoginUser(String token, Object loginUser) {
        String key = genKey(token);
        redisUtil.set(key, JSONObject.toJSONString(loginUser), EXPIRED, TimeUnit.HOURS);
    }

    public LoginUser getLoginUser(String token) {
        String key = genKey(token);
        return getLoginUserNoPrefix(key);
    }

    public LoginUser getLoginUserNoPrefix(String key){
        String str = redisUtil.get(key);
        if (Strings.isBlank(str)) {
            return null;
        }
        Long expire = redisUtil.getExpire(key);
        if (expire < 2000) { //续期token
            redisUtil.expire(key, EXPIRED, TimeUnit.HOURS);
        }
        return JSONObject.parseObject(str, LoginUser.class);
    }

    /**
     * 删除用户缓存
     *
     * @param token
     * @return
     */
    public boolean clearUser(String token) {
        String key = genKey(token);
        return this.clearUserNoPrefix(key);
    }


    /**
     * 删除用户缓存
     *
     * @param token
     * @return
     */
    public boolean clearUserNoPrefix(String token) {
        return redisUtil.delete(token);
    }

    /**
     * 刷新用户信息缓存
     *
     * @param request
     * @param loginUser
     */
    public void refreshLoginUser(HttpServletRequest request, Object loginUser) {
        String authorization = request.getHeader("Authorization");
        if (Strings.isNotBlank(authorization)) {
            String token = authorization.substring("Bearer ".length());
            String key = genKey(token);
            redisUtil.set(key, JSONObject.toJSONString(loginUser), EXPIRED, TimeUnit.HOURS);
        }

    }

}
