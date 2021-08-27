package com.zzh.dream.study.base.mapper;

import com.zzh.dream.study.base.entity.MyAccount;
import java.util.List;

public interface MyAccountMapper {
    int insert(MyAccount record);

    List<MyAccount> selectAll();
}