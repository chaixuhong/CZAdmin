/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.cz.aspect;

import com.cz.annotation.Limit;
import com.cz.exception.BusinessException;
import com.cz.utils.RequestParseUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * @author chaixuhong
 * @date 2021年12月30日
 */
@Aspect
@Component
@Slf4j
public class LimitAspect {

    public static final String REDIS_PREFIX = "limit:";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Pointcut("@annotation(com.cz.annotation.Limit)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = RequestParseUtil.getHttpServletRequest();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method signatureMethod = signature.getMethod();
        Limit limit = signatureMethod.getAnnotation(Limit.class);
        Limit.LimitType limitType = limit.limitType();
        StringBuffer key = new StringBuffer(REDIS_PREFIX).append(joinPoint.getTarget().getClass().getCanonicalName()).append(".").append(signatureMethod.getName());
        if (limitType == Limit.LimitType.IP) {
            key.append(":").append(RequestParseUtil.getIp(request));
        }
        List<String> keys = Arrays.asList(key.toString());
        DefaultRedisScript<Number> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("script/Limit.lua")));
        redisScript.setResultType(Number.class);
        Number count = stringRedisTemplate.execute(redisScript, keys, limit.count(), limit.period());
        if (null != count && count.intValue() <= Integer.parseInt(limit.count())) {
            log.info("第{}次访问key为 {}的接口,功能描述:{}", count, keys, limit.description());
            return joinPoint.proceed();
        } else {
            throw new BusinessException("访问次数受限制");
        }
    }

}
