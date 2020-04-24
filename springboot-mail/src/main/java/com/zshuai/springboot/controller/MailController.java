package com.zshuai.springboot.controller;

import com.zshuai.springboot.service.MailService;
import com.zshuai.springboot.vo.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zshuai
 *
 * @Date :2020/4/24 2:23 PM
 * @Version 1.0
 **/
@RestController
public class MailController {
    @Autowired
    private MailService mailService;


    @GetMapping(value = "/sendMail")
    public String sendEmail() {
        Email email_email = new Email();
        List<String> addressList = new ArrayList<String>();
        addressList.add("zshuai1722@163.com");
        email_email.setToAddress(addressList);
        // 主题
        email_email.setSubject("主题测试");
        email_email.setContent("你好！<br><br> 测试邮件发送成功！");
        // 发送邮件
        mailService.sendMail(email_email);

        return "ok";
    }
}
