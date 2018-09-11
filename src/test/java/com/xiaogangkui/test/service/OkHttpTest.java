package com.xiaogangkui.test.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xiaogangkui.util.common.HttpUtil;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Created by luchunyu
 */
public class OkHttpTest extends BaseTest{

    @Autowired
    private HttpUtil httpUtil;

    @Test
    public void testImage(){
        String result = httpUtil.get("https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials&client_id=R4B4v7ih5sCKtDQbGIEF4lHw&client_secret=2hEAfsI0IIX27oyxau6N4ToWdSDd3BXD");
        Map<String, String> map = JSON.parseObject(result, new TypeReference<Map<String, String>>() {
        });
        System.out.println(map);

    }

    @Test
    public void testScanImage() throws IOException {
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/vat_invoice?access_token=24.aa79fde9d279ada3457adda041235da6.2592000.1538135435.282335-11744150";
        Map<String,String> map = new HashMap<>();
        File file = new File("/Users/luchunyu/Desktop/WechatIMG173.png");
        map.put("image",URLEncoder.encode(encodeImgageToBase64(file),"utf-8"));
        Map<String,String> header = new HashMap<>();
        header.put("Content-Type","application/x-www-form-urlencoded");
        String result = httpUtil.postJsonByHeader(url, map, header);
        System.out.println(result);

    }

    public static String request(String httpUrl, String httpArg) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            // 填入apikey到HTTP header
            connection.setRequestProperty("apikey", "您自己的apikey");
            connection.setDoOutput(true);
            connection.getOutputStream().write(httpArg.getBytes("UTF-8"));
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String encodeImgageToBase64(File imageFile) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        // 其进行Base64编码处理
        byte[] data = null;
        // 读取图片字节数组
        try {
            InputStream in = new FileInputStream(imageFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);// 返回Base64编码过的字节数组字符串
    }
}
