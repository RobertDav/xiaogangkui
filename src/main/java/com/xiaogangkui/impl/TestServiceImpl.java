package com.xiaogangkui.impl;

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
}
