package com.xiaogangkui.util.handlerException;

import com.xiaogangkui.dto.ResultMap;
import com.xiaogangkui.util.common.ApplicationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Created by luchunyu
 */
@RestControllerAdvice
public class ApplicationHandlerException {

    @ExceptionHandler
    public ResultMap handler(HttpServletRequest request, HttpServletResponse response, Exception e){
        if(e instanceof NumberFormatException){
            System.out.println("数据类型转换异常");
        }
        if(e instanceof ApplicationException){
            return ResultMap.generate(500,e.getMessage());
        }
        return ResultMap.generate(500,"系统正在升级");
    }
}
