package com.cz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cz.cache.LoginUserCache;
import com.cz.entity.SysUser;
import com.cz.model.query.OnlineQuery;
import com.cz.model.vo.OnlineUserVO;
import com.cz.security.bean.LoginUser;
import com.cz.service.IOnlineUserService;
import com.cz.utils.PageUtil;
import com.cz.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class OnlineUserServiceImpl implements IOnlineUserService {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private LoginUserCache loginUserCache;

    @Override
    public IPage<OnlineUserVO> getPages(String filter, Pageable pageable) {
        List<OnlineUserVO> all = getAll(filter);
        List list = PageUtil.toPage(pageable.getPageNumber(), pageable.getPageSize(), all);
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        IPage<OnlineUserVO> page = new Page<>();
        page.setPages(pageNumber);
        page.setSize(pageSize);
        page.setTotal(all.size());
        page.setRecords(list);
        return page;
    }

    public List<OnlineUserVO> getAll(String filter) {
        List<String> tokens = redisUtil.scan(LoginUserCache.PREFIX + "*");
        Collections.reverse(tokens);
        List<OnlineUserVO> onlineUsers = new ArrayList<>();
        for (String token : tokens) {
            LoginUser loginUser = loginUserCache.getLoginUserNoPrefix(token);
            SysUser sysUser = loginUser.getUser();
            OnlineUserVO onlineUserVO = new OnlineUserVO(sysUser, loginUser.getLoginTime(), loginUser.getJob(), loginUser.getDept().getPath(), loginUser.getIp(), loginUser.getAddress(), loginUser.getBrowser());
            if (Strings.isNotBlank(filter)) {
                if (sysUser.toString().contains(filter)) {
                    onlineUsers.add(onlineUserVO);
                }
            } else {
                onlineUsers.add(onlineUserVO);
            }
        }
        onlineUsers.sort((o1, o2) -> o2.getLoginTime().compareTo(o1.getLoginTime()));
        return onlineUsers;
    }

    @Override
    public void delUsers(@Validated @RequestBody Set<OnlineQuery> onlineQueries) {
        List<String> tokens = redisUtil.scan(LoginUserCache.PREFIX + "*");
        for (OnlineQuery onlineQuery : onlineQueries) {
            for (String token : tokens) {
                LoginUser loginUser = loginUserCache.getLoginUserNoPrefix(token);
                if (loginUser.getUser().getUserId().longValue() == onlineQuery.getUserId().longValue() && onlineQuery.getLoginTime().equals(loginUser.getLoginTime())) {
                    loginUserCache.clearUserNoPrefix(token);
                }
            }
        }
    }
}
