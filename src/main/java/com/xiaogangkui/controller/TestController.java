package com.xiaogangkui.controller;

import com.alibaba.fastjson.JSON;
import com.xiaogangkui.dto.CompanyDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Created by luchunyu
 */
@RequestMapping(value = "/test")
@RestController
public class TestController {

    @RequestMapping(value = "/testInterceptor",method = RequestMethod.POST)
    public String testInterceptor(@RequestBody CompanyDto companyDto){
        return JSON.toJSONString(companyDto);
    }
}
