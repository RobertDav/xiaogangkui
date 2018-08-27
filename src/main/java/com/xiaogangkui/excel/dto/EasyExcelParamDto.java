package com.xiaogangkui.excel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author Created by luchunyu
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EasyExcelParamDto {
    private Map<String,String> map;
}
