package com.xiaogangkui.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    @RequestMapping(value = "/logger",method = RequestMethod.GET)
    public String loggerTest(){
        Logger logger = LoggerFactory.getLogger(getClass());
        logger.error("hello world");
        return "hello world";
    }
}
