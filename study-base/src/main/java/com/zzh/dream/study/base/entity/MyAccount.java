package com.zzh.dream.study.base.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyAccount {
    private String id;

    private String account;

    private String name;
}