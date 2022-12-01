package com.zzh.cloud.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @description: nacos配置中心配置获取
 * @author: zhangzihao
 * @date: 01/12/2022
 **/
@RefreshScope
public class UserConfigInfo {

    @Value("${com.zzh.cloud.nocos.user.name}")
    private String name;

    @Value("${com.zzh.cloud.nocos.user.age}")
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
