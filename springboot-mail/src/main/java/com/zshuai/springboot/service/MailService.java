package com.zshuai.springboot.service;

import com.zshuai.springboot.vo.Email;

/**
 * Created by zshuai
 *
 * @Date :2020/4/24 2:15 PM
 * @Version 1.0
 **/
public interface MailService {

    //发送邮件
    void sendMail(Email email);
}
