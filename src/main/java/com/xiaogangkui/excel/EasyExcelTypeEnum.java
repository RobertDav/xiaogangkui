package com.xiaogangkui.excel;


import com.xiaogangkui.excel.impl.CompanyExcelServiceImpl;
import com.xiaogangkui.util.common.ApplicationException;

import java.util.Arrays;

/**
 * @author Created by luchunyu
 */
public enum EasyExcelTypeEnum {

    EMPLOYEE(1, CompanyExcelServiceImpl.class);

    private int code;

    private Class<? extends ExcelService> clazz;

    EasyExcelTypeEnum(int code, Class clazz){
        this.code = code;
        this.clazz = clazz;
    }

    public Class<? extends ExcelService> getClazz() {
        return clazz;
    }

    public int getCode() {
        return code;
    }


    public static EasyExcelTypeEnum getByCode(int code){
        return Arrays.stream(EasyExcelTypeEnum.values())
                .filter(easyExcelTypeEnum -> easyExcelTypeEnum.getCode() == code)
                .findFirst()
                .orElseThrow(()-> new ApplicationException("未查询到对应的类型"));

    }

}
