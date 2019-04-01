package com.xiaogangkui.controller;

import com.alibaba.fastjson.JSON;
import com.suanhu.image.service.ImageService;
import com.suanhu.image.service.dto.ImagePathDto;
import com.xiaogangkui.dto.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

import static com.xiaogangkui.dto.ResultMap.SUCCESS_CODE;

@RestController
@RequestMapping(value = "/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResultMap upload(@RequestParam("file") MultipartFile file) {
        ImagePathDto imagePathDto = getImagePathDto(file);

        Map<String, String> pathAndUrl = new HashMap<>();
        pathAndUrl.put("path", imagePathDto.getPath());
        pathAndUrl.put("url", imagePathDto.getUrl());

        return ResultMap.generate(SUCCESS_CODE,"",JSON.toJSON(pathAndUrl));
    }
    private ImagePathDto getImagePathDto(MultipartFile file) {
        ImagePathDto imagePathDto = new ImagePathDto("", "");
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                imagePathDto = imageService.uploadPublic(bytes);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return imagePathDto;
    }
}
