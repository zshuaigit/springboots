package com.zshuai.springboot.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.zshuai.springboot.entity.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zshuai
 *
 * Jwt工具类，生成jwt和验证
 * @Date :2020/4/24 10:47 AM
 * @Version 1.0
 **/
public class JwtUtil {

    /**
     * 秘钥
     */
    private static final String SECRET = "zshuai";

    /**
     * 过去时间
     */
    private static final Long EXPIRETIME = 1800L;//单位秒

    //生成用户的token，并设置超时时间
    public static String createToken(User user){
        //过期时间
        Date expireDate = new Date(System.currentTimeMillis() + EXPIRETIME * 1000);

        Map<String, Object> map = new HashMap<>();
        /**
         * 声明类型，这里是JWT
         * 声明加密的算法，通常直接使用HMAC SHA256
         */
        map.put("alg","HS256");
        map.put("type","JWT");
        String token = JWT.create()
                //添加头部信息
                .withHeader(map)
                //添加用户基本信息
                .withClaim("id", user.getId())
                .withClaim("userName",user.getUserName())
                .withClaim("name", user.getName())
                //设置过期时间
                .withExpiresAt(expireDate)
                //设置签发时间
                .withIssuedAt(new Date())
                //设置加密方式
                .sign(Algorithm.HMAC256(SECRET));
        return token ;

    }

    //解析token
    public static Map<String, Claim> verifyToken(String token){
        DecodedJWT jwt = null;
        JWTVerifier jwtVerifier = JWT.require( Algorithm.HMAC256(SECRET)).build();
        jwt = jwtVerifier.verify(token);

        return jwt.getClaims();
    }
}
