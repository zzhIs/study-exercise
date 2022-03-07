package com.zzh.dream.study.base.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 26/08/2021
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String userName;
    private Date createTime;
}
