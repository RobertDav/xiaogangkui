package com.xiaogangkui.controller;

import com.google.common.collect.Lists;
import com.xiaogangkui.dao.AttendanceDao;
import com.xiaogangkui.dto.*;
import com.xiaogangkui.service.AttendanceService;
import com.xiaogangkui.service.ReimbursementService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.xiaogangkui.dto.ResultMap.SUCCESS_CODE;

/**
 * @author Created by luchunyu
 */
@RestController
@RequestMapping(value = "/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private AttendanceDao attendanceDao;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResultMap save(@RequestBody AttendanceDto attendanceDto) {
        attendanceService.save(attendanceDto);
        return ResultMap.generate(SUCCESS_CODE);
    }
    @RequestMapping(value = "/load", method = RequestMethod.POST)
    public ResultMap load(@RequestBody AttendanceDto attendanceDto) {
        AttendanceDto result = attendanceService.findById(attendanceDto.getId());
        return ResultMap.generate(SUCCESS_CODE,"",result);
    }

    @RequestMapping(value = "/queryList",method = RequestMethod.POST)
    public ResultMap queryList(@RequestBody FuzzySearchDto fuzzySearchDto){
        fuzzySearchDto.setLimit( fuzzySearchDto.getEnd() -fuzzySearchDto.getStart());
        List<AttendanceDto> attendanceDtos = attendanceService.fuzzySearch(fuzzySearchDto);
        int count = attendanceDao.fuzzySearchCount(fuzzySearchDto);
        Map<String,Object> map = new HashMap<>();
        map.put("total",count);
        map.put("list",attendanceDtos);
        return ResultMap.generate(SUCCESS_CODE,"",map);
    }


    @RequestMapping(value = "/totalReport",method = RequestMethod.POST)
    public ResultMap totalReport(@RequestBody FuzzySearchDto fuzzySearchDto){
        return null;
    }
}
