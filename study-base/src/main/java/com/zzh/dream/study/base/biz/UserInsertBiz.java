package com.zzh.dream.study.base.biz;

import com.zzh.dream.study.base.entity.MyAccount;
import com.zzh.dream.study.base.entity.User;
import com.zzh.dream.study.base.service.MyAccountService;
import com.zzh.dream.study.base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 07/03/2022
 **/
@Component
public class UserInsertBiz {
    @Autowired
    private UserService userService;
    @Autowired
    private MyAccountService myAccountService;

    @Transactional(rollbackFor = RuntimeException.class)
    public void insert(){

        try {
            userService.insert(new User(12345678,"userName",new Date()));
        } catch (Exception e) {
            System.out.println("新增失败...");
//            e.printStackTrace();
        }

        myAccountService.insert(new MyAccount(UUID.randomUUID().toString(),"account","account"));
    }
}
