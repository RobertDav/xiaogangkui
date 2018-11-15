package com.xiaogangkui.controller;

import com.alibaba.fastjson.JSON;
import com.xiaogangkui.dto.CompanyDto;
import com.xiaogangkui.provider.service.DemoService;
import com.xiaogangkui.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Created by luchunyu
 */
@RequestMapping(value = "/company")
@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private DemoService demoService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        System.out.println("调用company开始");
        List<CompanyDto> companyDtos = companyService.queryAll();
        System.out.println("调用结束");
        return JSON.toJSONString(companyDtos);
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(){
        System.out.println("rpc调用开始");
        String robert = demoService.sayHello("robert");
        System.out.println(robert);
        return robert;
    }



}
