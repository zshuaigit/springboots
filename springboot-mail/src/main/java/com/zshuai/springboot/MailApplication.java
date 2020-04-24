package com.zshuai.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by zshuai
 *
 * @Date :2020/4/24 2:06 PM
 * @Version 1.0
 **/
@SpringBootApplication
public class MailApplication {
    public static void main(String[] args) {
        //java mail发邮件是附件名过长默认会被截断，附件名显示【tcmime.29121.29517.50430.bin】，主动设为false可正常显示附件名
        System.setProperty("mail.mime.splitlongparameters", "false");
        SpringApplication.run(MailApplication.class, args);
    }
}
