package com.cz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cz.entity.SysUser;
import com.cz.model.bo.SysUserBO;
import com.cz.model.bo.UpdateInfoBO;
import com.cz.model.bo.UpdatePassBO;
import com.cz.model.query.UserListQuery;
import com.cz.model.vo.SysUserVO;
import com.cz.security.bean.LoginUser;
import com.github.yulichang.base.MPJBaseService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 系统用户 服务类
 * </p>
 *
 * @author chaizi
 * @since 2021-12-06
 */
public interface ISysUserService extends MPJBaseService<SysUser> {
    /**
     * 修改头像
     *
     * @param multipartFile
     */
    LoginUser updateAvatar(MultipartFile multipartFile);

    /**
     * 修改用户个人信息
     *
     * @param updateInfoBO
     */
    void updateInfo(UpdateInfoBO updateInfoBO, long userId);

    /**
     * 修改用户密码
     *
     * @param updatePassBO
     */
    void updatePass(UpdatePassBO updatePassBO);

    /**
     * 查询部门下所有用户
     *
     * @param userListQuery
     * @param pageable
     * @return
     */
    IPage<SysUserVO> getAllUser(UserListQuery userListQuery, Pageable pageable);

    /**
     * 保存用户，包括用户部门与角色
     *
     * @param sysUserBO
     */
    void saveUserInfo(SysUserBO sysUserBO);

    /**
     * 更新用户
     *
     * @param sysUserBO
     */
    void updateUserInfo(SysUserBO sysUserBO);

    void delUsers(List<Integer> ids);
}
