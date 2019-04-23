package com.xiaogangkui.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaogangkui.dto.CompanyDto;
import com.xiaogangkui.dto.FuzzySearchDto;
import com.xiaogangkui.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/testPageBuilder")
public class TestPageBuilderController {

    @Autowired
    private CompanyService companyService;

    @PostMapping(value = "/testQueryInfo")
    public String testQueryInfo(@RequestBody FuzzySearchDto fuzzySearchDto){
        PageHelper.startPage(fuzzySearchDto.getPageNum(), fuzzySearchDto.getPageSize());
        List<CompanyDto> companyDtos = companyService.queryAll();
        PageInfo<CompanyDto> appsPageInfo = new PageInfo<>(companyDtos);
        return JSON.toJSONString(appsPageInfo);
    }
}
