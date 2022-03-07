package com.zzh.dream.study.base.mapper;

import com.zzh.dream.study.base.entity.MyAccount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MyAccountMapper {
    int insert(@Param("account") MyAccount account);

    List<MyAccount> selectAll();
}