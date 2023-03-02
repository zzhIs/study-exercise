package com.zzh.dream.study.base.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.io.Serializable;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 02/03/2023
 **/
@Data
public class AuthorExcelDTO implements Serializable {

    @Excel(name = "Authors")
    private String name;

    @Excel(name = "EmailAddresses")
    private String email;
}
