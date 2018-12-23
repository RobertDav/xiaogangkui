package com.xiaogangkui.controller;

import com.xiaogangkui.dto.*;
import com.xiaogangkui.service.AttendanceService;
import com.xiaogangkui.service.ReimbursementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.xiaogangkui.dto.ResultMap.SUCCESS_CODE;

/**
 * @author Created by luchunyu
 */
@RestController
@RequestMapping(value = "/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

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
        List<AttendanceDto> attendanceDtos = attendanceService.fuzzySearch(fuzzySearchDto);
        return ResultMap.generate(SUCCESS_CODE,"",attendanceDtos);
    }
}
