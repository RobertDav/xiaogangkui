package com.xiaogangkui.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class TestLoggerController {

    @Autowired
    private Logger logger;
    @RequestMapping(value = "/logger",method = RequestMethod.GET)
    public String loggerTest(){

        logger.error("hello world");
        return "hello world";
    }
}
