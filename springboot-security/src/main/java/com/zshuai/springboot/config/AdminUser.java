package com.zshuai.springboot.config;

import com.sun.xml.internal.bind.v2.TODO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * 自定义鉴权类
 */
public class AdminUser implements UserDetails {

    private String username;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO 可以定制化权限列表
        return Collections.EMPTY_LIST;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        // 这里设置账号是否已经过期
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // 这里设置账号是否已经被锁定
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 这里设置凭证是否过期
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 是否可用
        return true;
    }
}
