package com.zzh.dream.study.base.biz;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.hutool.core.io.IoUtil;
import com.zzh.dream.study.base.dto.AuthorExcelDTO;
import com.zzh.dream.study.base.dto.AuthorResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 02/03/2023
 **/
@Slf4j
@Component
public class ExcelParseBiz {


    public void parseAndDownload(String excelPath) {

        List<AuthorExcelDTO> authorExcelDTOS = null;
        try {
            authorExcelDTOS = ExcelImportUtil.importExcel(new File(excelPath), AuthorExcelDTO.class, new ImportParams());
        } catch (Exception e) {
            e.printStackTrace();
        }

        authorExcelDTOS.stream().forEach(it -> System.out.println(it));

        List<AuthorResultDTO> list = authorExcelDTOS.stream()
                .filter(it -> StringUtils.isNotBlank(it.getEmail()) && StringUtils.isNotBlank(it.getName())).flatMap(it -> {
            String[] emails = it.getEmail().split("; ");
            String[] names = it.getName().split("; ");
            List<AuthorResultDTO> result = new ArrayList<>();
            if (emails.length == names.length && emails.length != 1) {
                for (int i = 0; i < names.length; i++) {
                    String[] name = names[i].split(", ");
                    if (name.length==1) {
                        result.add(new AuthorResultDTO(emails[i], name[0], ""));
                    }else{
                        result.add(new AuthorResultDTO(emails[i], name[0], name[1]));
                    }
                }
                return result.stream();
            } else if (emails.length == 1) {
                String[] name = names[names.length - 1].split(", ");
                if (name.length ==1) {
                    result.add(new AuthorResultDTO(emails[0], name[0], name[0]));
                }else{
                    result.add(new AuthorResultDTO(emails[0], name[0], name[1]));
                }
                return result.stream();
            } else {
                log.error("错误数据:{}", it);
            }
            return result.stream();
        }).collect(Collectors.toList());

        write2Local(list,"/Users/it00013207/Downloads/xs/","authorEmail");
    }


    /**
     * 写入到本地
     *
     * @param list
     */
    public void write2Local(List<AuthorResultDTO> list, String filePath, String fileName) {
        File saveFile = new File(filePath);
        if (!saveFile.exists()) {
            saveFile.mkdirs();
        }
        FileOutputStream fos = null;
        try {
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), AuthorResultDTO.class, list);
            fos = new FileOutputStream(filePath + "/" + fileName + ".xls");
            workbook.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IoUtil.closeIfPosible(fos);
        }
    }


    /**
     * 动态文件头的写出
     */
    public void parse() {
        //设置excel动态列
        List<ExcelExportEntity> entity = new ArrayList<>();
        ExcelExportEntity excelExportEntity = new ExcelExportEntity("email", "email");
        entity.add(excelExportEntity);
        ExcelExportEntity excelExportEntity1 = new ExcelExportEntity("last", "last");
        entity.add(excelExportEntity1);
        ExcelExportEntity excelExportEntity2 = new ExcelExportEntity("first", "first");
        entity.add(excelExportEntity2);

        List<Map<String, String>> dateMapList = new ArrayList<>();
        write2Excel(entity, dateMapList, "");
    }

    /**
     * 填充excel信息
     *
     * @param entity   动态头信息
     * @param dataList 填充数据
     * @param fileName 文件名称
     * @return
     */
    private InputStream write2Excel(List<ExcelExportEntity> entity, List<Map<String, String>> dataList, String fileName) {
        ByteArrayOutputStream outputStream = null;
        try {
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(fileName, fileName), entity, dataList);
            outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(outputStream);
        }
        return null;
    }

}
