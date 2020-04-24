package com.zshuai.springboot.controller;

import com.zshuai.springboot.service.IndexService;
import com.zshuai.springboot.vo.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import javax.xml.transform.Source;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zshuai
 *
 * @Date :2020/4/7 5:32 PM
 * @Version 1.0
 **/
@RestController
@RequestMapping("/")
public class IndexController {


    @Autowired
    IndexService indexService;

    @GetMapping("/count")
    public Integer getCount() {
        Integer total =indexService.getCount();
        return total;
    }

    @GetMapping("/user/{id}")
    public User findUser(@PathVariable("id") String id) {
        return indexService.getUserById(id);
    }


    /**
     * 修改用户操作时候，会先删除Redis中的数据，之后再存入数据库
     * 逻辑下次查看该用户时，穿透查询，返回结果，存入Redis
     * @param user
     * @return
     */
    @PostMapping("/user")
    public Boolean updateUser(@RequestBody User user){
        user.setRegtime(new Date());
        final Boolean update = indexService.update(user);
        return update;
    }

    @DeleteMapping("/user/{id}")
    public Boolean deleteById(@PathVariable("id") String id){

        StringUtils.
        return indexService.deleteById(id);
    }
}
