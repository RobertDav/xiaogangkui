package com.xiaogangkui.util.common;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.read.context.AnalysisContext;
import com.alibaba.excel.read.event.AnalysisEventListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Created by luchunyu
 */
public class ExcelUtil {


    public  void importExcel(MultipartFile file, ExcelTypeEnum excelTypeEnum, BaseRowModel baseRowModel){
        try {
            InputStream inputStream = file.getInputStream();
            new ExcelReader(inputStream, excelTypeEnum,null, new AnalysisEventListener<List<BaseRowModel>>() {
                @Override
                public void invoke(List<BaseRowModel> object, AnalysisContext context) {
                    System.out.println( "当前页数"+context.getCurrentSheet().getSheetNo()+"当前行"+context.getCurrentRowNum()+"当前数据"+object);
                }

                @Override
                public void doAfterAllAnalysed(AnalysisContext context) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
