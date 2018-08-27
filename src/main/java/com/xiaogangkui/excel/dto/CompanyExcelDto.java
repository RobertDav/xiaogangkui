package com.xiaogangkui.excel.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Created by luchunyu
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyExcelDto extends BaseRowModel {
    @ExcelProperty(index = 0,value = "公司名称")
    private String companyName;
}
