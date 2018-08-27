package com.xiaogangkui.excel.impl;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.xiaogangkui.dto.CompanyDto;
import com.xiaogangkui.excel.ExcelService;
import com.xiaogangkui.excel.dto.EasyExcelParamDto;
import com.xiaogangkui.excel.dto.CompanyExcelDto;
import com.xiaogangkui.service.CompanyService;
import com.xiaogangkui.util.common.BeanMapperUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Created by luchunyu
 */
@Component
public class CompanyExcelServiceImpl implements ExcelService {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private BeanMapperUtil beanMapperUtil;

    @Override
    public void importExcel(MultipartFile file, EasyExcelParamDto paramDto) {

    }

    @Override
    public void exportExcel(EasyExcelParamDto paramDto, HttpServletResponse response) {
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ExcelWriter excelWriter = new ExcelWriter(outputStream, ExcelTypeEnum.XLSX);
        //根据条件查询员工
        List<CompanyDto> companyDtos = companyService.queryAll();
        if (CollectionUtils.isEmpty(companyDtos)) return;
        List<CompanyExcelDto> employeeExcelDtos = beanMapperUtil.batchMapper(companyDtos, CompanyExcelDto.class);
        try {
            Sheet sheet = new Sheet(1, 2, CompanyExcelDto.class, "公司列表", null);
            excelWriter.write(employeeExcelDtos, sheet);
            response.setContentType("application/x-msdownload;");
            response.setHeader("Content-disposition", "attachment; filename=" + new String("公司列表".getBytes("UTF-8"), "ISO-8859-1") + ExcelTypeEnum.XLSX.getValue());
            outputStream.flush();
        } catch (Exception e) {

        } finally {
            excelWriter.finish();
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
