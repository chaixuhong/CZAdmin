package com.cz.security.bean;


import com.alibaba.fastjson.annotation.JSONField;
import com.cz.entity.SysDept;
import com.cz.entity.SysRole;
import com.cz.entity.SysUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * 用户封装类
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginUser implements UserDetails, Serializable {

    private static final long serialVersionUID = -4504630943184309233L;

    private SysUser user;

    private List<SysRole> roles;

    private SysDept dept;

    private String job;

    private List<Integer> dataScope;

    private List<String> authority;

    private String loginTime;

    private String ip;

    private String address;

    private String browser;

    @JSONField(serialize = false)
    private List<GrantedAuthority> authorities;

    @Override
    @JSONField(serialize = false)
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    @JSONField(serialize = false)
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    @JSONField(serialize = false)
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JSONField(serialize = false)
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JSONField(serialize = false)
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JSONField(serialize = false)
    public boolean isEnabled() {
        return user.getEnabled();
    }


}
