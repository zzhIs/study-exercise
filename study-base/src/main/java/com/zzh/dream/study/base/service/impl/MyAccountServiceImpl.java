package com.zzh.dream.study.base.service.impl;

import com.zzh.dream.study.base.entity.MyAccount;
import com.zzh.dream.study.base.mapper.MyAccountMapper;
import com.zzh.dream.study.base.service.MyAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 07/03/2022
 **/
@Service
public class MyAccountServiceImpl implements MyAccountService {
    @Autowired
    private MyAccountMapper  myAccountMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void insert(MyAccount account) {
        myAccountMapper.insert(account);
    }
}
