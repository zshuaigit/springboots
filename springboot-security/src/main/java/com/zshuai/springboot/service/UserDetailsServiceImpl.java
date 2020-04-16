package com.zshuai.springboot.service;

import com.zshuai.springboot.config.AdminUser;
import com.zshuai.springboot.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by zshuai
 *
 * @Date :2020/4/16 11:41 AM
 * @Version 1.0
 **/
@Service
public class UserDetailsServiceImpl  implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //TODO 模拟在数据库根据用户名查询用户信息
        User user = new User(1, "zshuai", "zshuai");
        // 构建 UserDetails 的实现类 => AdminUser
        return new AdminUser(user.getUsername());
    }
}
