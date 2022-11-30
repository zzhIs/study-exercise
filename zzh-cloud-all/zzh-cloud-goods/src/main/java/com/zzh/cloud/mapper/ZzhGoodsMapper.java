package com.zzh.cloud.mapper;

import com.zzh.cloud.entity.ZzhGoods;
import java.util.List;

public interface ZzhGoodsMapper {
    int deleteByPrimaryKey(String id);

    int insert(ZzhGoods record);

    ZzhGoods selectByPrimaryKey(String id);

    List<ZzhGoods> selectAll();

    int updateByPrimaryKey(ZzhGoods record);
}