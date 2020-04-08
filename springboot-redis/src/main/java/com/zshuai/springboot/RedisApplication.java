package com.zshuai.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Created by zshuai
 *
 * @Date :2020/4/7 5:08 PM
 * @Version 1.0
 **/
@SpringBootApplication
@MapperScan("com.zshuai.springboot.repository")//注意MapperScan要导tk的包，不然会报NoSuchMethodException
public class RedisApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class);
    }
}
