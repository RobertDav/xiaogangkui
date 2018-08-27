package com.xiaogangkui.controller;

import com.xiaogangkui.excel.EasyExcelTypeEnum;
import com.xiaogangkui.excel.ExcelService;
import com.xiaogangkui.excel.dto.EasyExcelParamDto;
import com.xiaogangkui.util.common.BeanUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * @author Created by luchunyu
 */
@RestController
@RequestMapping(value = "/excel")
public class ExcelController {

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void exportExcel(@RequestParam("code") int code
            , HttpServletRequest request
            , HttpServletResponse response) {
        ExcelService service = getServiceByCode(code);
        service.exportExcel(getParamDto(request),response);
    }

    @RequestMapping(value = "/import",method = RequestMethod.POST)
    public void importExcel(@RequestParam("file") MultipartFile file
            , int  code
            ,HttpServletRequest request){
        ExcelService service = getService(code);
        service.importExcel(file,getParamDto(request));
    }

    private ExcelService getServiceByCode(int code){
        EasyExcelTypeEnum easyExcelTypeEnum = EasyExcelTypeEnum.getByCode(code);
        return BeanUtil.getBean(easyExcelTypeEnum.getClazz());
    }

    private ExcelService getService(int code){
        EasyExcelTypeEnum easyExcelTypeEnum = EasyExcelTypeEnum.getByCode(code);
        String clazzName = easyExcelTypeEnum.getClazz().getSimpleName();
        Map<String, ExcelService> beans = BeanUtil.getApplicationContext().getBeansOfType(ExcelService.class);
        Optional<String> first = beans.keySet().stream().filter(bean -> bean.equalsIgnoreCase(clazzName)).findFirst();
        return beans.get(first.get());
    }


    private EasyExcelParamDto getParamDto(HttpServletRequest request){
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String,String> map = new HashMap<>();
        Set<String> sets = parameterMap.keySet();
        sets.forEach(key->map.put(key,parameterMap.get(key)[0]));
        EasyExcelParamDto paramDto = EasyExcelParamDto.builder()
                .map(map)
                .build();
        return paramDto;
    }
}
