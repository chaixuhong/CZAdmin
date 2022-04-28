package com.cz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cz.config.FilesProperties;
import com.cz.constant.CommonConstant;
import com.cz.entity.SysUser;
import com.cz.entity.SysUsersRoles;
import com.cz.enums.ResultEnum;
import com.cz.exception.BusinessException;
import com.cz.exception.GlobalException;
import com.cz.mapper.SysUserMapper;
import com.cz.model.bo.SysUserBO;
import com.cz.model.bo.UpdateInfoBO;
import com.cz.model.bo.UpdatePassBO;
import com.cz.model.query.UserListQuery;
import com.cz.model.vo.SysUserVO;
import com.cz.security.bean.LoginUser;
import com.cz.security.utils.SecurityUtil;
import com.cz.service.ISysUserService;
import com.cz.service.ISysUsersRolesService;
import com.cz.utils.FilesUtil;
import com.cz.utils.PageUtil;
import com.cz.utils.QueryUtil;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author chaizi
 * @since 2021-12-06
 */
@Service
public class SysUserServiceImpl extends MPJBaseServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Resource
    private FilesProperties properties;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private ISysUsersRolesService sysUsersRolesService;

    @Override
    public LoginUser updateAvatar(MultipartFile multipartFile) {
        // 文件大小验证
        FilesUtil.validateSize(properties.getAvatarMaxSize(), multipartFile.getSize());
        // 验证文件上传的格式
        String fileType = FilesUtil.getFileType(FilesUtil.getExtensionName(multipartFile.getOriginalFilename()));
        if (!fileType.equals(FilesUtil.IMAGE)) {
            throw new GlobalException(ResultEnum.FILE_TYPE_ERROR);
        }
        //查询用户信息
        LoginUser loginUser = SecurityUtil.getLoginUser();
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(SysUser::getUserId, loginUser.getUser().getUserId());
        SysUser user = this.getOne(queryWrapper);
        String oldPath = user.getAvatarPath();
        //保存头像文件
        File file = FilesUtil.upload(multipartFile, properties.getPath().getAvatar());
        user.setAvatarPath(Objects.requireNonNull(file).getPath());
        user.setAvatarName(file.getName());
        //更新用户
        this.updateById(user);
        //删除旧头像文件
        if (StringUtils.isNotBlank(oldPath)) {
            FilesUtil.del(oldPath);
        }
        loginUser.setUser(user);
        return loginUser;
    }

    @Override
    public void updateInfo(UpdateInfoBO updateInfoBO, long userId) {
        SysUser user = this.getOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getPhone, updateInfoBO.getPhone()));
        if (!Objects.isNull(user) && !user.getUserId().equals(userId)) {
            throw new BusinessException("手机号已被占用");
        }
        SysUser update = new SysUser();
        update.setUserId(userId).setNickName(updateInfoBO.getNickName()).setPhone(updateInfoBO.getPhone())
                .setGender(updateInfoBO.getGender()).setUpdateTime(LocalDateTime.now()).setUpdateBy(SecurityUtil.getLoginUserName());
        this.updateById(update);
    }

    @Override
    public void updatePass(UpdatePassBO updatePassBO) {
        SysUser user = this.getById(SecurityUtil.getLoginUser().getUser().getUserId());
        if (!passwordEncoder.matches(updatePassBO.getOldPass(), user.getPassword())) {
            throw new BusinessException("旧密码验证失败");
        }
        LambdaUpdateWrapper<SysUser> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(SysUser::getPassword, passwordEncoder.encode(updatePassBO.getNewPass())).set(SysUser::getPwdResetTime, LocalDateTime.now())
                .eq(SysUser::getUserId, user.getUserId());
        this.update(updateWrapper);
    }

    @Override
    public IPage<SysUserVO> getAllUser(UserListQuery userListQuery, Pageable pageable) {
        PageUtil<SysUser> pageUtil = new PageUtil<>(pageable);
        MPJQueryWrapper<SysUser> wrapper = QueryUtil.buildWrapper(userListQuery,null);
        MPJLambdaWrapper<SysUser> queryWrapper = new MPJLambdaWrapper<>();
        queryWrapper.select(SysUser::getUserId, SysUser::getUsername, SysUser::getDeptId, SysUser::getNickName, SysUser::getAvatarName, SysUser::getJobId)
                .select(SysUser::getGender, SysUser::getPhone, SysUser::getEmail, SysUser::getIsAdmin, SysUser::getEnabled, SysUser::getCreateTime, SysUser::getCreateBy);
        wrapper.select(queryWrapper.getSqlSelect());
        wrapper.select("dept.name deptName,dept.path deptPath,job.name");
        wrapper.leftJoin("sys_job job on t.job_id = job.job_id ");
        return this.selectJoinListPage(pageUtil, SysUserVO.class, wrapper);
    }

    @Transactional
    @Override
    public void saveUserInfo(SysUserBO sysUserBO) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserBO, sysUser);
        sysUser.setCreateTime(LocalDateTime.now()).setCreateBy(SecurityUtil.getLoginUserName())
                .setIsAdmin(false).setPassword(passwordEncoder.encode(CommonConstant.DEFAULT_PASSWORD));
        this.save(sysUser);
        if (sysUserBO.getRoles().size() > 0) {
            List<SysUsersRoles> sysUsersRolesList = Lists.newArrayList();
            for (Integer id : sysUserBO.getRoles()) {
                SysUsersRoles sysUsersRoles = new SysUsersRoles();
                sysUsersRoles.setUserId(sysUser.getUserId()).setRoleId(id);
                sysUsersRolesList.add(sysUsersRoles);
            }
            sysUsersRolesService.saveBatch(sysUsersRolesList);
        }

    }

    @Transactional
    @Override
    public void updateUserInfo(SysUserBO sysUserBO) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserBO, sysUser);
        sysUser.setUpdateBy(SecurityUtil.getLoginUserName()).setUpdateTime(LocalDateTime.now());
        this.updateById(sysUser);
        if (sysUserBO.getRoles().size() > 0) {
            sysUsersRolesService.remove(new LambdaQueryWrapper<SysUsersRoles>().eq(SysUsersRoles::getUserId, sysUser.getUserId()));
            List<SysUsersRoles> sysUsersRolesList = Lists.newArrayList();
            for (Integer id : sysUserBO.getRoles()) {
                SysUsersRoles sysUsersRoles = new SysUsersRoles();
                sysUsersRoles.setUserId(sysUser.getUserId()).setRoleId(id);
                sysUsersRolesList.add(sysUsersRoles);
            }
            sysUsersRolesService.saveBatch(sysUsersRolesList);
        }
    }


    @Transactional
    @Override
    public void delUsers(List<Integer> ids) {
        this.removeByIds(ids);
        sysUsersRolesService.remove(new LambdaQueryWrapper<SysUsersRoles>().in(SysUsersRoles::getUserId, ids));
    }
}
