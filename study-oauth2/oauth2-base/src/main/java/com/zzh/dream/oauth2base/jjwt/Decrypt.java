package com.zzh.dream.oauth2base.jjwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 14/05/2022
 **/
public class Decrypt {
    public static final String signature = "ZZH520";

    public static void main(String[] args) {
        //token
        String token ="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2NjYiLCJzdWIiOiJGb3giLCJpYXQiOjE2NTI0OTYzODEsImV4cCI6MTY1MjQ5NjQ0MSwicm9sZXMiOiJhZG1pbiIsImxvZ28iOiJ4eHguanBnIn0.LzPodVNexGoUULjbMPmZg_7AfWqXA-4QLP0oZAuT8_U";
        //解析token获取载荷中的声明对象
        Claims claims = Jwts.parser()
                .setSigningKey(signature)
                .parseClaimsJws(token)
                .getBody();

        System.out.println("id:"+claims.getId());
        System.out.println("subject:"+claims.getSubject());
        System.out.println("issuedAt:"+claims.getIssuedAt());

        DateFormat sf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("签发时间:"+sf.format(claims.getIssuedAt()));
        System.out.println("过期时间:"+sf.format(claims.getExpiration()));
        System.out.println("当前时间:"+sf.format(new Date()));

        System.out.println("roles:"+claims.get("roles"));
        System.out.println("logo:"+claims.get("logo"));
    }
}
