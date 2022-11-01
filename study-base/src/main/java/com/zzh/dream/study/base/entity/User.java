package com.zzh.dream.study.base.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 26/08/2021
 **/
@Data
@Accessors(chain = true)
public class User {
    private Integer id;
    private String userName;
    private Date createTime;

    public User(Integer id, String userName, Date createTime) {
        this.id = id;
        this.userName = userName;
        this.createTime = createTime;
    }

    public User() {
    }

    @Transient
    private List<MyAccount> accountList;
}
