package com.cz.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cz.annotation.Logger;
import com.cz.constant.CommonConstant;
import com.cz.entity.SysLog;
import com.cz.exception.BusinessException;
import com.cz.mapper.SysLogMapper;
import com.cz.model.LogQuery;
import com.cz.service.ISysLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cz.utils.FilesUtil;
import com.cz.utils.PageUtil;
import com.cz.utils.QueryUtil;
import com.cz.utils.RequestParseUtil;
import com.github.yulichang.query.MPJQueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>
 * 系统日志 服务实现类
 * </p>
 *
 * @author chaizi
 * @since 2021-12-03
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements ISysLogService {

    @Override
    public void saveLog(String username, String browser, String ip, ProceedingJoinPoint joinPoint, SysLog sysLog) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Logger logger = method.getAnnotation(Logger.class);
        // 方法路径
        String methodName = joinPoint.getTarget().getClass().getName() + "." + signature.getName() + "()";
        // 描述
        sysLog.setDescription(logger.value());
        sysLog.setRequestIp(ip);//解析ip
        sysLog.setAddress(RequestParseUtil.getCityInfo(sysLog.getRequestIp()));
        sysLog.setMethod(methodName);
        sysLog.setUsername(username);
        sysLog.setParams(getParameter(method, joinPoint.getArgs()));
        sysLog.setBrowser(browser);
        sysLog.setCreateDate(LocalDate.now());
        sysLog.setCreateTime(LocalDateTime.now());
        save(sysLog);
    }

    /**
     * 根据方法和传入的参数获取请求参数
     */
    private String getParameter(Method method, Object[] args) {
        List<Object> argList = new ArrayList<>();
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            //将RequestBody注解修饰的参数作为请求参数
            RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
            if (requestBody != null) {
                argList.add(args[i]);
            }
            //将RequestParam注解修饰的参数作为请求参数
            RequestParam requestParam = parameters[i].getAnnotation(RequestParam.class);
            if (requestParam != null) {
                Map<String, Object> map = new HashMap<>();
                String key = parameters[i].getName();
                if (!StringUtils.isEmpty(requestParam.value())) {
                    key = requestParam.value();
                }
                map.put(key, args[i]);
                argList.add(map);
            }
        }
        if (argList.size() == 0) {
            return "";
        }
        return argList.size() == 1 ? JSONUtil.toJsonStr(argList.get(0)) : JSONUtil.toJsonStr(argList);
    }

    @Override
    public IPage<SysLog> findLoginUserLogs(String username, Pageable pageable) {
        PageUtil<SysLog> pages = new PageUtil<>(pageable);
        LambdaQueryWrapper<SysLog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysLog::getUsername,username).eq(SysLog::getLogType, Logger.Type.INFO.name());
        return this.page(pages,queryWrapper);
    }


    @Override
    public IPage<SysLog> findLogs(LogQuery query, Pageable pageable) {
        PageUtil<SysLog> pages = new PageUtil<>(pageable);
        MPJQueryWrapper<SysLog> sysLogMPJQueryWrapper = QueryUtil.buildWrapper(query,null);
        LambdaQueryWrapper<SysLog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(SysLog::getLogId,SysLog::getUsername,SysLog::getMethod,SysLog::getParams,SysLog::getRequestIp,SysLog::getDescription
        ,SysLog::getBrowser,SysLog::getCreateTime,SysLog::getAddress,SysLog::getTime,SysLog::getResult);
        sysLogMPJQueryWrapper.select(queryWrapper.getSqlSelect());
        return this.page(pages,sysLogMPJQueryWrapper);
    }


    @Override
    public String findLogsException(Long logId) {
        LambdaQueryWrapper<SysLog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(SysLog::getExceptionDetail).eq(SysLog::getLogId,logId);
        SysLog sysLog = this.getOne(queryWrapper);
        if (Objects.isNull(sysLog)) return "";
        return sysLog.getExceptionDetail();
    }


    @Override
    public void delAllLog(String logType) {
        LambdaQueryWrapper<SysLog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysLog::getLogType,logType);
        this.remove(queryWrapper);
    }


    @Override
    public List<SysLog> queryAll(LogQuery query) {
        MPJQueryWrapper<SysLog> queryWrapper = QueryUtil.buildWrapper(query,SysLog.class);
        return this.list(queryWrapper);
    }


    @Override
    public void download(List<SysLog> sysLogs, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (SysLog log : sysLogs) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("用户名", log.getUsername());
            map.put("IP", log.getRequestIp());
            map.put("IP来源", log.getAddress());
            map.put("描述", log.getDescription());
            map.put("浏览器", log.getBrowser());
            map.put("请求耗时/毫秒", log.getTime());
            map.put("异常详情", log.getExceptionDetail());
            map.put("创建日期", Objects.isNull(log.getCreateTime()) ? "-" : log.getCreateTime().toString());
            list.add(map);
        }
        FilesUtil.downloadExcel(list, response);
    }
}
