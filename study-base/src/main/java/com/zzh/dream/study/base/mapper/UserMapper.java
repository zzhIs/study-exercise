package com.zzh.dream.study.base.mapper;

import com.zzh.dream.study.base.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 26/08/2021
 **/
public interface UserMapper {

    User selectById(@Param("id") Integer id);

    void insert(@Param("user") User user);
}
