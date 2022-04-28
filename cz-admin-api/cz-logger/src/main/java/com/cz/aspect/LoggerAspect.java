package com.cz.aspect;

import com.alibaba.fastjson.JSONObject;
import com.cz.annotation.Logger;
import com.cz.constant.CommonConstant;
import com.cz.entity.SysLog;
import com.cz.exception.BusinessException;
import com.cz.service.ISysLogService;
import com.cz.utils.RequestParseUtil;
import com.cz.utils.ThrowableUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
@Slf4j
public class LoggerAspect {
    @Autowired
    private ISysLogService logService;


    ThreadLocal<Long> currentTime = new ThreadLocal<>();

    /**
     * 配置切入点
     */
    @Pointcut("@annotation(com.cz.annotation.Logger)")
    public void logPointcut() {
        // 该方法无方法体,主要为了让同类中其他方法使用此切入点
    }


    @Around("logPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result;
        currentTime.set(System.currentTimeMillis());
        result = joinPoint.proceed();
        SysLog sysLog = new SysLog(Logger.Type.INFO.name(), System.currentTimeMillis() - currentTime.get());
        sysLog.setResult(JSONObject.toJSONString(result));
        currentTime.remove();
        HttpServletRequest request = RequestParseUtil.getHttpServletRequest();
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        logService.saveLog(name, RequestParseUtil.getBrowser(request), RequestParseUtil.getIp(request), joinPoint, sysLog);
        return result;
    }


    /**
     * 异常日志
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "logPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        SysLog sysLog = new SysLog(Logger.Type.ERROR.name(), System.currentTimeMillis() - currentTime.get());
        currentTime.remove();
        System.out.println(e instanceof BusinessException);
        if(e instanceof BusinessException){
            sysLog.setExceptionDetail(e.getMessage());
        }else{
            sysLog.setExceptionDetail(ThrowableUtil.getStackTrace(e));
        }
        HttpServletRequest request = RequestParseUtil.getHttpServletRequest();
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        logService.saveLog(name, RequestParseUtil.getBrowser(request), RequestParseUtil.getIp(request), (ProceedingJoinPoint)joinPoint, sysLog);
    }

}
