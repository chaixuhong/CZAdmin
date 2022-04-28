package com.cz.controller.auth;


import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.JSONObject;
import com.cz.annotation.Logger;
import com.cz.cache.CodeCache;
import com.cz.cache.LoginUserCache;
import com.cz.constant.CommonConstant;
import com.cz.exception.BusinessException;
import com.cz.model.bo.UpdateInfoBO;
import com.cz.model.bo.UpdatePassBO;
import com.cz.model.vo.MenusVO;
import com.cz.model.vo.ResultVO;
import com.cz.security.bean.LoginUser;
import com.cz.security.utils.SecurityUtil;
import com.cz.service.ISysMenuService;
import com.cz.service.ISysUserService;
import com.cz.utils.CodeGeneratedUtil;
import com.cz.utils.ResponseUtil;
import com.cz.utils.RsaUtils;
import com.wf.captcha.ArithmeticCaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@Api(tags = "登录人：相关操作")
@RestController
@RequestMapping("/auth")
@Validated
public class AuthController {

    @Autowired
    private CodeCache codeCache;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private LoginUserCache loginUserCache;

    @GetMapping(value = "/code")
    @ApiOperation(value = "获取登录验证码图片")
    public ResultVO<Object> getCode() {
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(111, 36);
        captcha.getArithmeticString();  // 获取运算的公式：4-9+1=?
        String text = captcha.text();// 获取运算的结果：-4
        System.out.println("获取运算的结果" + text);
        String uuid = CodeGeneratedUtil.genUUID();
        codeCache.setCode(uuid, text);
        return ResponseUtil.success(MapUtil.builder().put("img", captcha.toBase64()).put("uuid", uuid).build());
    }

    @GetMapping("/user")
    @ApiOperation("获取当前登录人信息")
    public ResultVO info(@ApiIgnore Authentication authentication) {
        //这里toJson是为了和login接口返回的数据保持一致，将值为null的字段干掉
        return ResponseUtil.success(JSONObject.parse(JSONObject.toJSONString(authentication.getPrincipal())));
    }

    @GetMapping(value = "/menus")
    @ApiOperation("获取当前登录人左侧菜单")
    public ResultVO<List<MenusVO>> getMenusTree() {
        return ResponseUtil.success(menuService.getMenusTree());
    }


    @ApiOperation("当前登录人修改头像")
    @PostMapping(value = "/updateAvatar")
    public ResultVO<String> updateAvatar(@NotNull(message = "数据不能为空") @RequestParam MultipartFile avatar, HttpServletRequest request) {
        LoginUser loginUser = userService.updateAvatar(avatar);
        loginUserCache.refreshLoginUser(request, loginUser);
        return ResponseUtil.success(loginUser.getUser().getAvatarName());
    }

    @Logger("当前登录人修改个人信息")
    @ApiOperation("当前登录人修改个人信息")
    @PostMapping(value = "/updateInfo")
    public ResultVO updateInfo(@Validated @RequestBody UpdateInfoBO updateInfoBO, HttpServletRequest request) {
        LoginUser loginUser = SecurityUtil.getLoginUser();
        userService.updateInfo(updateInfoBO, loginUser.getUser().getUserId());
        loginUser.getUser().setNickName(updateInfoBO.getNickName()).setPhone(updateInfoBO.getPhone()).setGender(updateInfoBO.getGender())
                .setEmail(updateInfoBO.getEmail());
        loginUserCache.refreshLoginUser(request, loginUser);
        return ResponseUtil.success();
    }


    @ApiOperation("修改登录密码")
    @PostMapping(value = "/updatePass")
    public ResultVO updatePass(@Validated @RequestBody UpdatePassBO updatePassBO) {
        try {
            String oldPass = RsaUtils.decryptByPrivateKey(CommonConstant.RAS_PRIVATE_KEY, updatePassBO.getOldPass());
            String newPass = RsaUtils.decryptByPrivateKey(CommonConstant.RAS_PRIVATE_KEY, updatePassBO.getNewPass());
            updatePassBO.setOldPass(oldPass);
            updatePassBO.setNewPass(newPass);
        } catch (Exception e) {
            throw new BusinessException("密码格式错误");
        }
        userService.updatePass(updatePassBO);
        return ResponseUtil.success();
    }



}

