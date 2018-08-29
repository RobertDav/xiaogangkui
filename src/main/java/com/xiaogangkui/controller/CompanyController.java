package com.xiaogangkui.controller;

import com.alibaba.fastjson.JSON;
import com.xiaogangkui.dto.CompanyDto;
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

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index() {
        System.out.println("调用company开始");
        List<CompanyDto> companyDtos = companyService.queryAll();
        System.out.println("调用结束");
        return JSON.toJSONString(companyDtos);
    }
}
