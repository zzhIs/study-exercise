package com.zzh.dream.study.base.controller;

import com.zzh.dream.study.base.biz.ExcelParseBiz;
import com.zzh.dream.study.base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 操作excel的控制器
 * @author: zhangzihao
 * @date: 26/08/2021
 **/
@RestController
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    private ExcelParseBiz excelParseBiz;

    @PostMapping("/parse_download")
    public String excel(@RequestParam("excelPath") String excelPath){

        excelParseBiz.parseAndDownload(excelPath);
        return "success";
    }
}
