package com.xiaogangkui.excel;


import com.xiaogangkui.excel.dto.EasyExcelParamDto;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Created by luchunyu
 */
public interface ExcelService {
    /**
     * 导入Excel
     *
     * @param file
     * @param paramDto
     */
    void importExcel(MultipartFile file, EasyExcelParamDto paramDto);

    /**
     * 导出excel
     *
     * @param paramDto
     * @param response
     */
    void exportExcel(EasyExcelParamDto paramDto, HttpServletResponse response);
}
