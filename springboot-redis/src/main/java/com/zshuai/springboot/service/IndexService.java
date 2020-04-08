package com.zshuai.springboot.service;

import com.zshuai.springboot.repository.UserDao;
import com.zshuai.springboot.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
 * Created by zshuai
 *
 * @Date :2020/4/8 9:58 AM
 * @Version 1.0
 **/
@Service
public class IndexService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserDao userDao;

    public Integer getCount() {
        //从缓存中取得字符串数据
        String count = (String)redisTemplate.opsForValue().get("count");
        Integer total = Integer.parseInt(count == null ? "0" : count);
        //将数据存入缓存
        redisTemplate.opsForValue().set("count", String.valueOf(total+1));
        return total;
    }

    public User getUserById(String id) {
        //从缓存中提取
        User user= (User)redisTemplate.opsForValue().get("user_"+id);
        // 如果缓存没有则到数据库查询并放入缓存
        if(user==null) {
            System.out.println("123323");
            user = userDao.selectByPrimaryKey(id);
            //添加缓存过期时间，方便测试，设定为10 s
           redisTemplate.opsForValue().set("user_" + id, user,10, TimeUnit.SECONDS);
           //redisTemplate.opsForValue().set("user_" + id, user);
            System.out.println("从数据库查询的");

        }
        System.out.println("缓存中查询");
        return user;
    }


    @Transactional
    public Boolean update(User user) {
        //删除缓存
        redisTemplate.delete( "user_" + user.getId() );
        int i = userDao.updateByPrimaryKey(user);
        if (i!=0){
            return true;
        }else {
            return false;
        }
    }


    /*** 删除 * @param id */
    @Transactional
    public Boolean deleteById(String id) {
        //删除缓存
        redisTemplate.delete( "user_" + id );
        int i = userDao.deleteByPrimaryKey(id);
        if (i!=0){
            return true;
        }else {
            return false;
        }
    }
}
