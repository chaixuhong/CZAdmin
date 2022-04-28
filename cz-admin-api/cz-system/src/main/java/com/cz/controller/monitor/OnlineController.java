package com.cz.controller.monitor;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cz.annotation.Logger;
import com.cz.cache.LoginUserCache;
import com.cz.model.query.OnlineQuery;
import com.cz.model.vo.OnlineUserVO;
import com.cz.model.vo.ResultVO;
import com.cz.service.IOnlineUserService;
import com.cz.utils.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/monitor/online")
@Api(tags = "系统：在线用户管理")
public class OnlineController {

    @Autowired
    private IOnlineUserService onlineUserService;

    @Autowired
    private LoginUserCache loginUserCache;

    @ApiOperation("查询在线用户")
    @GetMapping
    @PreAuthorize("hasAuthority('online:list')")
    public ResultVO<IPage<OnlineUserVO>> query(String filter, @ApiIgnore Pageable pageable) {
        return ResponseUtil.success(onlineUserService.getPages(filter, pageable));
    }


    @ApiOperation("踢出用户")
    @DeleteMapping
    @Logger("踢出用户")
    @PreAuthorize("hasAuthority('online:del')")
    public ResultVO delete(@RequestBody Set<OnlineQuery> onlineQueries) {
        onlineUserService.delUsers(onlineQueries);
        return ResponseUtil.success();
    }
}
