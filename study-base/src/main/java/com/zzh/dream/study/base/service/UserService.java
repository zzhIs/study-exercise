package com.zzh.dream.study.base.service;

import com.zzh.dream.study.base.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 26/08/2021
 **/
public interface UserService {

    User selectById(Integer id);

    void insert(User user);

    User getUserDetailById(@Param("id") Integer id);
}
