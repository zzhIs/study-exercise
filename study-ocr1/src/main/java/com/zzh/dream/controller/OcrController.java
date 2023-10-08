package com.zzh.dream.controller;

import com.zzh.dream.OCRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 18/07/2023
 **/
@RestController
@RequestMapping("/ocr")
public class OcrController {
    @Autowired
    private OCRService ocrService;

    @PostMapping("/recognize")
    public String recognizeText(@RequestParam("image") String imagePath) throws Exception {
        return ocrService.recognize(imagePath);
    }

}
