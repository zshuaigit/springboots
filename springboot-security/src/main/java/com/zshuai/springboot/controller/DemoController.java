package com.zshuai.springboot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by zshuai
 *
 * @Date :2020/4/16 9:51 AM
 * @Version 1.0
 **/
@Api(value = "securityDemo的简单演示", description = "简单实现springboot-security功能")
@RestController
@RequestMapping("api")
public class DemoController {

    @ApiOperation(httpMethod = "GET", value = "演示需要权限登录状态")
    @GetMapping("needlogin/test")
    public String  test1() {
        return "ok";
    }
    @ApiOperation(httpMethod = "GET", value = "演示不需要权限登录状态")
    @GetMapping("notneedlogin/test")
    public String  test2() {
        return "OK";
    }

    @ApiOperation(httpMethod = "GET", value = "演示不需要权限登录状态")
    @GetMapping(value = "notneedlogin/login")
    public Object login(HttpSession session) {
        // MOCK 模拟登陆
        session.setAttribute("username", "zshuai");
        return "OK";
    }


}
