package com.cz.cache;

import com.cz.constant.CommonConstant;
import com.cz.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
@Component
public class CodeCache {
    @Autowired
    private RedisUtil redisUtil;

    private final String PREFIX = CommonConstant.REDIS_PREFIX + ":login:code:";

    private final long EXPIRED = 5L;

    private String genKey(String key) {
        return PREFIX + key;
    }

    public void setCode(String uuid, String code) {
        redisUtil.set(genKey(uuid), code, EXPIRED, TimeUnit.MINUTES);
    }

    public String getCode(String uuid) {
        String str = redisUtil.get(genKey(uuid));
        return str;
    }

    public boolean clearCode(String token) {
        return redisUtil.delete(genKey(token));
    }

}
