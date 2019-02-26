package com.xiaogangkui.impl;

import com.xiaogangkui.dto.CompanyDto;
import com.xiaogangkui.service.TestService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;



@Component
public class TestServiceImpl implements TestService {

    @Autowired
    private Logger logger;

    @Override
    @Cacheable(value = "loadInfo",key = "#key")
    public String loadInfo(String key) {
        logger.error("这是一个测试");
        return key;
    }

    @Override
    @Cacheable(value = "testLoad")
    public String testLoad() {
        logger.error("这是一个测试");
        return "testLoad";
    }

    @Override
    @Cacheable(value = "test",key = "#companyDto.companyName")
    public CompanyDto redisTest(CompanyDto companyDto) {
        logger.error("这是一个测试1");
        return companyDto;
    }

    @Override
    @Cacheable(value = "test",key = "#companyDto.id")
    public Integer redisTest(CompanyDto companyDto,String name) {
        logger.error("这是一个测试2");
        return companyDto.getId();
    }
}
