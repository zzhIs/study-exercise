package com.zzh.cloud.mapper;

import com.zzh.cloud.entity.ZzhOrder;
import java.util.List;

public interface ZzhOrderMapper {
    int deleteByPrimaryKey(String id);

    int insert(ZzhOrder record);

    ZzhOrder selectByPrimaryKey(String id);

    List<ZzhOrder> selectAll();

    int updateByPrimaryKey(ZzhOrder record);
}