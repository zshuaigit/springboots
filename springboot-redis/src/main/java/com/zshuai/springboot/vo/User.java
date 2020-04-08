package com.zshuai.springboot.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "t_users")//mybatis通用接口mapper依赖JPA实体类采用JPA
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements Serializable {

    // 主键 自动递增
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 管理员编号
    @Column(name = "address")
    private String address;

    // 姓名
    @Column(name = "name")
    private String name;

    // 邮箱
    @Column(name = "age")
    private Integer age;

    @Column(name = "roles_id")
    private Integer rolesId;

    // 注册时间 格式化
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "regtime")
    private Date regtime;

}
