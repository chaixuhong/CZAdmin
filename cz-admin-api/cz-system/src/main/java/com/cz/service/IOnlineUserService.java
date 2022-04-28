package com.cz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cz.model.query.OnlineQuery;
import com.cz.model.vo.OnlineUserVO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

/**
 * 在线用户
 */
public interface IOnlineUserService {

    IPage<OnlineUserVO> getPages(String filter, Pageable pageable);

    void delUsers(Set<OnlineQuery> onlineQueries);
}
