package com.xiaogangkui.controller;

import com.xiaogangkui.util.common.QRCodeUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/testDownloadZip")
public class TestDownloadToZipController {

    @GetMapping(value = "/download")
    public void download(HttpServletResponse response){
        Map<String,String> map = new HashMap<>();
        map.put("hello","hello");
        map.put("hello1","hello1");
        map.put("hello2","hello2");
        QRCodeUtil.download(map,response);
    }
}
