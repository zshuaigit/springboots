package com.zshuai.springboot.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.util.List;

/**
 * Created by zshuai
 *
 * @Date :2020/4/24 2:09 PM
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Email {
    /**
     * 发件人地址(为空时自动调用配置文件中设置的发件人)
     */
//    private String fromAddress;

    /**
     * 收件人地址
     */
    private List<String> toAddress;

    /**
     * 抄送人地址
     */
    private List<String> ccAddress;

    /**
     * 密送人地址
     */
    private List<String> bccAddress;

    /**
     * 邮件主题
     */
    private String subject;

    /**
     * 邮件内容
     */
    private String content;

    /**
     * 附件
     */
    private List<File> attachments;
}
