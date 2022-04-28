package com.cz.controller.monitor;

import com.cz.model.vo.ResultVO;
import com.cz.service.IMonitorService;
import com.cz.utils.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Zheng Jie
 * @date 2020-05-02
 */
@RestController
@RequiredArgsConstructor
@Api(tags = "系统-服务监控管理")
@RequestMapping("/monitor")
public class MonitorController {

    @Autowired
    private IMonitorService serverService;

    @GetMapping
    @ApiOperation("查询服务监控")
    @PreAuthorize("hasAuthority('monitor:list')")
    public ResultVO<Map<String,Object>> query() {
        return ResponseUtil.success(serverService.getServers());
    }
}
