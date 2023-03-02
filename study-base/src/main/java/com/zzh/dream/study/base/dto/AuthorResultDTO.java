package com.zzh.dream.study.base.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 02/03/2023
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorResultDTO implements Serializable {

    @Excel(name = "email")
    private String email;

    @Excel(name = "last")
    private String last;

    @Excel(name = "first")
    private String first;
}
