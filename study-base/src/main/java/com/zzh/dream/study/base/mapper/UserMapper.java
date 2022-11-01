package com.zzh.dream.study.base.mapper;

import com.zzh.dream.study.base.entity.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 26/08/2021
 **/
public interface UserMapper extends Mapper<User> {

    User selectById(@Param("id") Integer id);

    int insert(@Param("user") User user);

    User getUserDetailById(@Param("id")Integer id);
}
