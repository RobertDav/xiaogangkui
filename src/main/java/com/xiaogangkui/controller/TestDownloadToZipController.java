package com.xiaogangkui.controller;

import com.xiaogangkui.util.common.QRCodeUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/testDownloadZip")
public class TestDownloadToZipController {

    @GetMapping(value = "/download")
    public void download(HttpServletResponse response){
        Map<String,String> map = new HashMap<>();
        map.put("https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQEw8TwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyU3VBc2RoTTNiaS0xMDAwMGcwM1AAAgRnwa5YAwQAAAAA","中文");
        map.put("http://ocemkcav3.bkt.clouddn.com/FgkMqjtKcDoHP5KDyFmO2M6pmEE4","我就是中文hhh");
        QRCodeUtil.urlToqrcodeZip(map,response);
    }

}
