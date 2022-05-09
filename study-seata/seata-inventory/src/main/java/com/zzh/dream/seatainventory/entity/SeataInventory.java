package com.zzh.dream.seatainventory.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class SeataInventory {
    private String id;

    private String name;

    private String goodsId;

    private Integer count;

    private String crtName;

    private Date crtTime;

    private String uptName;

    private Date uptTime;
}