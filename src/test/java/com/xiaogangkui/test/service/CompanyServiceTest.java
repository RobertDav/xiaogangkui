package com.xiaogangkui.test.service;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.StoreFollowurlGetRequest;
import com.taobao.api.response.StoreFollowurlGetResponse;
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
    static String taourl= "http://gw.api.taobao.com/router/rest";
    static String crmtaourl= "https://eco.taobao.com/router/rest";
    static String appkey = "23436132";
    static String crmappkey = "23034260";
    static String appsecret = "2967d008d7b5a02058fe718bacb0018f";
    static String crmappsecret = "740b45fc42265159d148f3196289f0ad";
    static String crmranddom_num = "hKBZnr5FEKE+yJrUhiUgW5RhD/0yZb1jIScjZpMgPZ4=";
    static String sessionKey = "62006084d82da75fhj02a12f890d2e056c3de7bf00044012943025980";

    static String sx_taourl= "http://gw.api.taobao.com/router/rest";
    static String sxs_taourl= "https://gw.api.tbsandbox.com/router/rest";
    static String sx_appkey = "1023034260";
    static String sx_appsecret = "sandboxc42265159d148f3196289f0ad";
    static String sx_sessionKey = "6102015fec6ec0981fe753a1e70a0caa3072e7ce7bc1a592054718217";
    static String sx_random_num = "U5C1D2MxKpQPBzmldt7DJbDEPoMPnpczrBS3sKVH+ac=";
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
    @Test
    public void test(){

        TaobaoClient client = new DefaultTaobaoClient(taourl, appkey, appsecret);
        StoreFollowurlGetRequest req = new StoreFollowurlGetRequest();
        req.setCallbackUrl("https://guerlain.ews.m.jaeapp.com/interact-center/follow_shopConcern?console=true");
        req.setUserNick("guerlain娇兰官方旗舰店");
        req.setUserId(2943025980L);
        StoreFollowurlGetResponse rsp = null;
        try {
            rsp = client.execute(req, sessionKey);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        System.out.println(rsp.getUrl());
    }
}
