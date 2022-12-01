package com.zzh.cloud.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Accessors(chain = true)
@Data
public class ZzhOrder {
    private String id;

    private String orderName;

    private String orderNum;

    private Integer price;

    private String crtName;

    private Date crtTime;

    private String uptName;

    private Date uptTime;
}