package com.zzh.dream.seataorder.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 订单表
 */
@Data
@Accessors(chain = true)
public class SeataOrder {
    private String id;

    private String orderName;

    private String goodsId;

    private String status;

    private Date crtTime;
}