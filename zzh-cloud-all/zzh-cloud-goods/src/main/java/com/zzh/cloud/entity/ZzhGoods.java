package com.zzh.cloud.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;


@Data
@Accessors(chain = true)
public class ZzhGoods {
    private String id;

    private String goodsName;

    private String goodsNum;

    private String goodsColor;

    private Integer count;

    private String crtName;

    private Date crtTime;

    private String uptName;

    private Date uptTime;

}