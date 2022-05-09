package com.zzh.dream.seatainventory.mapper;

import com.zzh.dream.seatainventory.entity.SeataInventory;
import java.util.List;

public interface SeataInventoryMapper {
    int deleteByPrimaryKey(String id);

    int insert(SeataInventory record);

    SeataInventory selectByPrimaryKey(String id);

    List<SeataInventory> selectAll();

    int updateByPrimaryKey(SeataInventory record);

    SeataInventory selectByGoodsId(String goodId);
}