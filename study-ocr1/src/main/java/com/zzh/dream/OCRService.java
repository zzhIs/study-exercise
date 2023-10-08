package com.zzh.dream;

import net.sourceforge.tess4j.ITessAPI;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.io.File;
import java.io.IOException;


/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 18/07/2023
 **/
@Component
public class OCRService {

    public static final String default_language = "chi_sim";

    public String recognize(String imagePath, String language) throws IOException, TesseractException {
        File imageFile = new File(imagePath);
        ITesseract tesseract = new Tesseract();
        tesseract.setDatapath(getClass().getResource("/tessdata").getPath()); // 设置tessdata默认目录
        tesseract.setLanguage(language); // 设置识别语言
        String recognizedText = tesseract.doOCR(imageFile); // 执行 OCR
        return recognizedText;
    }

    public String recognize(String imagePath) throws IOException, TesseractException {
        return recognize(imagePath, default_language);
    }


}
