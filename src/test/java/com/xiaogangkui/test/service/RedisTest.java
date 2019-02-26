package com.xiaogangkui.test.service;


import com.xiaogangkui.service.TestService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisTest extends BaseTest{

    @Autowired
    private TestService testService;

    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void testLoad(){
        System.out.println( testService.loadInfo("testHello"));
    }
    @Test
    public void testTemplate(){
        redisTemplate.opsForValue().set("hello","world");
        Object testKey = redisTemplate.opsForValue().get("hello");
        System.out.println(testKey);
    }
}
