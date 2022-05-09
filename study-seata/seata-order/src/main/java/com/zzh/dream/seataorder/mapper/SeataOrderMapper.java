package com.zzh.dream.seataorder.mapper;

import com.zzh.dream.seataorder.entity.SeataOrder;
import java.util.List;

public interface SeataOrderMapper {
    int deleteByPrimaryKey(String id);

    int insert(SeataOrder record);

    SeataOrder selectByPrimaryKey(String id);

    List<SeataOrder> selectAll();

    int updateByPrimaryKey(SeataOrder record);
}