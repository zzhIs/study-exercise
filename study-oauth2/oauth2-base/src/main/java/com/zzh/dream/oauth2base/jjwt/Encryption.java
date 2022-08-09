package com.zzh.dream.oauth2base.jjwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.Base64Codec;

import java.util.Date;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 14/05/2022
 **/
public class Encryption {

    //秘钥
    public static final String signature = "ZZH520";

    public static void main(String[] args) {
        //创建一个JwtBuilder对象
        JwtBuilder jwtBuilder = Jwts.builder()
                //声明的标识{"jti":"666"}
                .setId("666")
                //主体，用户{"sub":"Fox"}
                .setSubject("zzh")
                //创建日期{"ita":"xxxxxx"}
                .setIssuedAt(new Date())
                //设置过期时间   1分钟
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 1000))
                //直接传入map
                // .addClaims(map)
                .claim("roles", "admin")
                .claim("logo", "xxx.jpg")
                //签名手段，参数1：算法，参数2：盐
                .signWith(SignatureAlgorithm.HS256, signature);
        //获取token
        String token = jwtBuilder.compact();
        System.out.println(token);

        //三部分的base64解密
        System.out.println("=========");
        String[] split = token.split("\\.");
        System.out.println(Base64Codec.BASE64.decodeToString(split[0]));
        System.out.println(Base64Codec.BASE64.decodeToString(split[1]));
        //无法解密
        System.out.println(Base64Codec.BASE64.decodeToString(split[2]));
    }
}
