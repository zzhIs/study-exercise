package com.zzh.dream.study.base.core.xml.sax;

import lombok.Data;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 03/11/2021
 **/
@Data
public class Book {
    private String id;
    private String name;
    private String year;
    private String price;
    private String language;
    private String author;
}
