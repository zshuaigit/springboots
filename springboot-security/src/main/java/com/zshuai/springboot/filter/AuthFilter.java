package com.zshuai.springboot.filter;

import com.zshuai.springboot.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zshuai
 *  过滤器，拦截请求判断是否登陆，组装UserDetails：
 * @Date :2020/4/16 9:48 AM
 * @Version 1.0
 **/
@Component
public class AuthFilter extends OncePerRequestFilter {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        String username = (String)httpServletRequest.getSession().getAttribute("username");
        if (username != null && !"".equals(username)){
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            //不为null且可用
            if (userDetails != null && userDetails.isEnabled()){
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails,
                                null, userDetails.getAuthorities());
                // 把当前登陆用户放到上下文中
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(
                        httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }else{
                // 用户不合法，清除上下文
                SecurityContextHolder.clearContext();
            }
        }
        //放行
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
