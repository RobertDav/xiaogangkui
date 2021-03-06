package com.xiaogangkui.util.handlerException;

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
    public void handler(HttpServletRequest request, HttpServletResponse response, Exception e){
        if(e instanceof NumberFormatException){
            System.out.println("数据类型转换异常");
        }
    }
}
