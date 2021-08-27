package com.zzh.dream.study.base.entity;


import lombok.Data;

import java.util.Date;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 26/08/2021
 **/
@Data
public class User {
    private String id;
    private String userName;
    private Date createTime;
}
