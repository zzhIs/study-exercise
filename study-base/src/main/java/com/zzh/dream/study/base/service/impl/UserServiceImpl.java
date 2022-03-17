package com.zzh.dream.study.base.service.impl;

import com.zzh.dream.study.base.entity.User;
import com.zzh.dream.study.base.mapper.UserMapper;
import com.zzh.dream.study.base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;

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
    public User selectById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class,propagation = Propagation.REQUIRES_NEW)
    public void insert(User user) {
        userMapper.insert(user);
    }
}
