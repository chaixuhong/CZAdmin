package com.cz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cz.entity.SysLog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cz.model.LogQuery;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 系统日志 服务类
 * </p>
 *
 * @author chaizi
 * @since 2021-12-03
 */
public interface ISysLogService extends IService<SysLog> {

    /**
     * 异步保存日志
     *
     * @param username
     * @param browser
     * @param ip
     * @param joinPoint
     * @param sysLog
     */
    @Async
    void saveLog(String username, String browser, String ip, ProceedingJoinPoint joinPoint, SysLog sysLog);

    /**
     * 分页查询当前登录人列表
     *
     * @param username
     * @param pageable
     * @return
     */
    IPage<SysLog> findLoginUserLogs(String username, Pageable pageable);

    /**
     * 分页查询日志列表
     *
     * @param query
     * @param pageable
     * @return
     */
    IPage<SysLog> findLogs(LogQuery query, Pageable pageable);

    /**
     * 查询异常日志异常信息
     *
     * @param logId
     * @return
     */
    String findLogsException(Long logId);

    /**
     * 清空日志
     *
     * @param logType
     */
    void delAllLog(String logType);

    /**
     * 查询所有日志
     * @param query
     * @return
     */
    List<SysLog> queryAll(LogQuery query);

    /**
     * 下载日志
     * @param queryAll
     * @param response
     */
    void download(List<SysLog> queryAll, HttpServletResponse response) throws IOException;
}
