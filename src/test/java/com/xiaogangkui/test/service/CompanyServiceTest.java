package com.xiaogangkui.test.service;

import com.xiaogangkui.dto.CompanyDto;
import com.xiaogangkui.service.CompanyService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

/**
 * @author Created by luchunyu
 */
public class CompanyServiceTest extends BaseTest {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void testQueryAll(){
        List<CompanyDto> companyDtos = companyService.queryAll();
        System.out.println(companyDtos);
    }
    @Test
    public void testRedis(){
//        redisTemplate.opsForValue().set("helloWorld","testHelloWorld");
        System.out.println(redisTemplate.opsForValue().get("helloWorld"));
    }
}
