package com.zshuai.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * Created by zshuai
 *
 * @Date :2020/4/24 11:28 AM
 * @Version 1.0
 **/
@SpringBootApplication
@ServletComponentScan(basePackages = "com.zshuai.springboot.filter")
public class JwtApplication {
    public static void main(String[] args) {
        SpringApplication.run(JwtApplication.class);
    }
}
