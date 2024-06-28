package com.zzh.dream.study.base.biz;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.write.metadata.WriteSheet;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @description: excel导出, 支持动态sheet动态表头
 * @author: zhangzihao
 * @date: 2024-06-25
 **/
@Component
public class ExcelExportBiz {

    private static final Logger LOGGER = Logger.getLogger(ExcelExportBiz.class.getName());

    /**
     * @param dataMap   Map<sheet名称,输出集合List<BaseUseInfoItem>>
     * @param directory 输出的目录
     * @param fileName  文件名称
     * @throws IOException
     */
    public void exportExcel(Map<String, List<?>> dataMap, String directory, String fileName) throws IOException {
        // Excel文件路径
        String filePath = directory + File.separator + fileName + ".xlsx";
        // 创建ExcelWriter对象，用于写入Excel文件
        ExcelWriter excelWriter = EasyExcel.write(new File(filePath)).build();
        try {
            // 遍历数据集合，每个集合写入一个sheet
            for (Map.Entry<String, List<?>> entry : dataMap.entrySet()) {
                String sheetName = entry.getKey();
                List<?> data = entry.getValue();
                Class<?> clazz = data.get(0).getClass();
                // 假设所有集合元素类型一致，这里简单获取类型
                WriteSheet writeSheet = EasyExcel.writerSheet(sheetName).head(clazz).build();
                excelWriter.write(data, writeSheet);
            }
        } finally {
            // 完成写入后，关闭writer
            excelWriter.finish();
            LOGGER.info("Excel文件已成功导出到：" + filePath);
        }
    }


    @Data
    @Accessors(chain = true)
    public class BaseUseInfoItem {

        @ExcelProperty(value = "用户ID", order = 1)
        private String userId;

        @ExcelProperty(value = "姓名", order = 2)
        private String name;

        @ExcelProperty(value = "身份证号", order = 3)
        private String idCard;

        @ExcelProperty(value = "医疗编号", order = 4)
        private String medicalNo;

        @ExcelProperty(value = "医疗信息", order = 5)
        private String medicalValue;

        @ExcelProperty(value = "备注", order = 6)
        private String note;
    }


}
