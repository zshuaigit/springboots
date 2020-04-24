package com.zshuai.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by zshuai
 *
 * @Date :2020/4/24 10:51 AM
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
public class User {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String userName;
    private String name;
    private String password;
}