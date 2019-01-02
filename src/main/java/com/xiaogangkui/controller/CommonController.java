package com.xiaogangkui.controller;

import com.xiaogangkui.dao.CommonDao;
import com.xiaogangkui.dto.CommonDto;
import com.xiaogangkui.dto.FuzzySearchDto;
import com.xiaogangkui.dto.ResultMap;
import com.xiaogangkui.util.common.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.xiaogangkui.dto.ResultMap.ERROR_CODE;
import static com.xiaogangkui.dto.ResultMap.SUCCESS_CODE;

/**
 * @author Created by luchunyu
 */
@RestController
@RequestMapping(value = "/common")
public class CommonController {

    @Autowired
    private CommonDao commonDao;

    @RequestMapping(value = "/queryAllProject", method = RequestMethod.POST)
    public ResultMap queryAllProject(@RequestBody FuzzySearchDto fuzzySearchDto) {
        List<CommonDto> commonDtos = commonDao.queryAllProject(fuzzySearchDto);
        return ResultMap.generate(SUCCESS_CODE, "", commonDtos);
    }

    @RequestMapping(value = "/queryAllUser", method = RequestMethod.POST)
    public ResultMap queryAllUser(@RequestBody FuzzySearchDto fuzzySearchDto) {
        List<CommonDto> commonDtos = commonDao.queryAllUser(fuzzySearchDto);
        return ResultMap.generate(SUCCESS_CODE, "", commonDtos);
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResultMap login(@RequestBody FuzzySearchDto fuzzySearchDto) {
        String md5 = MD5Util.MD5(fuzzySearchDto.getPassword());
        fuzzySearchDto.setPassword(md5);
        CommonDto commonDto = commonDao.loadByAccount(fuzzySearchDto);
        return ResultMap.generate(SUCCESS_CODE,"",commonDto);
    }

    @RequestMapping(value = "/modifyPassword",method = RequestMethod.POST)
    public ResultMap modifyPassword(@RequestBody FuzzySearchDto fuzzySearchDto){
        CommonDto commonDto = commonDao.loadByUserId(fuzzySearchDto);
        if(!commonDto.getPassword().equals(MD5Util.MD5(fuzzySearchDto.getPassword()))){
            return ResultMap.generate(ERROR_CODE,"密码错误");
        }else {
            commonDao.updateUserPassword(fuzzySearchDto.getId(),MD5Util.MD5(fuzzySearchDto.getNewPassword()));
            return ResultMap.generate(SUCCESS_CODE);
        }

    }
}
