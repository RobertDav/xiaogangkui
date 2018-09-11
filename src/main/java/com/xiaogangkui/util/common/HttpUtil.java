package com.xiaogangkui.util.common;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import okhttp3.*;
import org.springframework.beans.factory.InitializingBean;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author Created by luchunyu
 */
public class HttpUtil implements InitializingBean {

    private static MediaType JSON_TYPE = MediaType.parse("application/json");
    private static MediaType FORM_TYPE = MediaType.parse("application/x-www-form-urlencoded");

    private OkHttpClient okHttpClient;


    public HttpUtil(){

    }
    public HttpUtil(OkHttpClient okHttpClient){
        this.okHttpClient = okHttpClient;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if(Objects.isNull(okHttpClient)){
            okHttpClient = new OkHttpClient();
        }
    }

    public void setOkHttpClient(OkHttpClient okHttpClient){
        this.okHttpClient = okHttpClient;
    }

    public String get(String url){
        Request request = new Request.Builder().url(url).build();
        return excute(request);
    }

    public String postJson(String url,Object object){
        RequestBody requestBody = RequestBody.create(JSON_TYPE, JSON.toJSONString(object));
        Request request = new Request.Builder().url(url).post(requestBody).build();
        return excute(request);
    }


    public String postJsonByHeader(String url,Object object,Map<String,String> header){
        RequestBody requestBody = RequestBody.create(JSON_TYPE, JSON.toJSONString(object));
        Set<String> set = header.keySet();

        Request.Builder request = new Request.Builder().url(url).post(requestBody);
        for (String s : set) {
            request.addHeader(s,header.get(s));
        }
        return excute(request.build());

    }

    public String excute(Request request){
        try {
            Response execute = okHttpClient.newCall(request).execute();
            return execute.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 支持Object value 为 String 类型 和Map<String,String>
     * @param url
     * @param object
     * @return
     */
    public String postForm(String url,Object object){
        Map<String,String> map = new HashMap<>();
        if(object instanceof Map){
            map =  (Map<String,String>)object;
        }else{
            map = JSON.parseObject(JSON.toJSONString(object), new TypeReference<Map<String, String>>() {
            });
        }
        Set<Map.Entry<String, String>> entries = map.entrySet();
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : entries) {
            builder.add( entry.getKey(),entry.getValue());
        }
        FormBody formBody = builder.build();
        Request request = new Request.Builder().url(url).post(formBody).build();
        return excute(request);
    }
}
