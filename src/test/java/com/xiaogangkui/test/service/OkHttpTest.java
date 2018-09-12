package com.xiaogangkui.test.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xiaogangkui.util.common.HttpUtil;
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
public class OkHttpTest extends BaseTest {

    @Autowired
    private HttpUtil httpUtil;

    public static String request(String httpUrl, String httpArg) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        try {
            // 用java JDK自带的URL去请求
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // 设置该请求的消息头
            // 设置HTTP方法：POST
            connection.setRequestMethod("POST");
            // 设置其Header的Content-Type参数为application/x-www-form-urlencoded
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            // 填入apikey到HTTP header
//            connection.setRequestProperty("apikey", "uml8HFzu2hFd8iEG2LkQGMxm");
            // 将第二步获取到的token填入到HTTP header
            connection.setRequestProperty("access_token", "24.d1efb53ac654bc39c66086a56494a4f4.2592000.1539246175.282335-11744515");
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

    @Test
    public void testImage() {
        String result = httpUtil.get("https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials&client_id=83xP82Vmh3wTgGv6wtRKd2HL&client_secret=KV7HctuyM7j0IXm2RGW6yccfkLRfdRSE");
        Map<String, String> map = JSON.parseObject(result, new TypeReference<Map<String, String>>() {
        });
        System.out.println(map);

    }

    @Test
    public void testScanImage() throws IOException {
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/vat_invoice?access_token=24.d1efb53ac654bc39c66086a56494a4f4.2592000.1539246175.282335-11744515";
        Map<String, String> map = new HashMap<>();
        File file = new File("/Users/luchunyu/Desktop/WechatIMG173.png");
        map.put("image", URLEncoder.encode(encodeImgageToBase64(file), "utf-8"));
        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/x-www-form-urlencoded");
        String result = httpUtil.postJsonByHeader(url, map, header);
        System.out.println(result);

    }

    @Test
    public void testScan() {
        // 获取本地的绝对路径图片
        File file = new File("/Users/luchunyu/Desktop/WechatIMG173.jpg");
        // 进行BASE64位编码
        String imageBase = encodeImgageToBase64(file);
        imageBase = imageBase.replaceAll("\r\n", "");
        imageBase = imageBase.replaceAll("\\+", "%2B");
        // 百度云的文字识别接口,后面参数为获取到的token
        String httpUrl = "https://aip.baidubce.com/rest/2.0/ocr/v1/vat_invoice?access_token=24.d1efb53ac654bc39c66086a56494a4f4.2592000.1539246175.282335-11744515";
        String httpArg = "image=" + imageBase;
        String jsonResult = request(httpUrl, httpArg);
        System.out.println("返回的结果--------->" + jsonResult);
    }

    public String encodeImgageToBase64(File imageFile) {
        // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        // 其进行Base64编码处理
        byte[] data = null;
        // 读取图片字节数组
        try {
            InputStream in = new FileInputStream(imageFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);// 返回Base64编码过的字节数组字符串
    }


}
