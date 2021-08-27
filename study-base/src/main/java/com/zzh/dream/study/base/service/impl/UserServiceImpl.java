package com.zzh.dream.study.base.service.impl;

import com.zzh.dream.study.base.entity.User;
import com.zzh.dream.study.base.mapper.UserMapper;
import com.zzh.dream.study.base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 26/08/2021
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectById(String id) {
        return userMapper.selectById(id);
    }
}
