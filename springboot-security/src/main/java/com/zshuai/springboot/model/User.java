package com.zshuai.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by zshuai
 *
 * @Date :2020/4/16 11:43 AM
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;

    private String username;

    private String password;
}
