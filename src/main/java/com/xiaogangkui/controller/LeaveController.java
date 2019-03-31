package com.xiaogangkui.controller;

import com.google.common.collect.Lists;
import com.xiaogangkui.dto.FuzzySearchDto;
import com.xiaogangkui.dto.LeaveRecordDto;
import com.xiaogangkui.dto.LeaveVerifyRecordDto;
import com.xiaogangkui.dto.ResultMap;
import com.xiaogangkui.service.LeaveService;
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
@RequestMapping(value = "/leave")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @RequestMapping(value = "/searchList", method = RequestMethod.POST)
    public ResultMap searchList(@RequestBody FuzzySearchDto fuzzySearchDto) {
        List<LeaveRecordDto> leaveRecordDtos = leaveService.fuzzySearch(fuzzySearchDto);
        List<LeaveRecordDto> result = Lists.newArrayList();
        if(CollectionUtils.isNotEmpty(leaveRecordDtos)){
            result = leaveRecordDtos.subList(fuzzySearchDto.getStart(), leaveRecordDtos.size() > fuzzySearchDto.getEnd() ? fuzzySearchDto.getEnd() : leaveRecordDtos.size());
        }
        Map<String,Object> map = new HashMap<>();
        map.put("total",leaveRecordDtos.size());
        map.put("list",result);
        return ResultMap.generate(SUCCESS_CODE,"",map);
    }

    @RequestMapping(value = "/loadById", method = RequestMethod.POST)
    public ResultMap loadById(@RequestBody FuzzySearchDto fuzzySearchDto) {
        LeaveRecordDto recordDto = leaveService.findById(fuzzySearchDto.getId());
        return ResultMap.generate(SUCCESS_CODE, "", recordDto);
    }

    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    public ResultMap updateStatus(@RequestBody LeaveRecordDto recordDto) {
        leaveService.updateStatus(recordDto.getId(), recordDto.getStatus());
        return ResultMap.generate(SUCCESS_CODE);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResultMap save(@RequestBody LeaveRecordDto leaveRecordDto) {
        leaveService.save(leaveRecordDto);
        return ResultMap.generate(SUCCESS_CODE);
    }

    @RequestMapping(value = "/verifyLeave", method = RequestMethod.POST)
    public ResultMap verifyLeave(@RequestBody LeaveVerifyRecordDto recordDto) {
        leaveService.verifyLeave(recordDto);
        return ResultMap.generate(SUCCESS_CODE);
    }

    @RequestMapping(value = "/queryVerifyList", method = RequestMethod.POST)
    public ResultMap queryVerifyList(@RequestBody FuzzySearchDto fuzzySearchDto) {
        List<LeaveRecordDto> leaveRecordDtos = leaveService.queryVerifyList(fuzzySearchDto);
        List<LeaveRecordDto> result = Lists.newArrayList();
        if(CollectionUtils.isNotEmpty(leaveRecordDtos)){
            result = leaveRecordDtos.subList(fuzzySearchDto.getStart(), leaveRecordDtos.size() > fuzzySearchDto.getEnd() ? fuzzySearchDto.getEnd() : leaveRecordDtos.size());
        }
        Map<String,Object> map = new HashMap<>();
        map.put("total",leaveRecordDtos.size());
        map.put("list",result);
        return ResultMap.generate(SUCCESS_CODE,"",map);
    }


    @RequestMapping(value = "/queryLeaveList", method = RequestMethod.POST)
    public ResultMap queryLeaveList(@RequestBody FuzzySearchDto fuzzySearchDto) {
        List<LeaveRecordDto> leaveRecordDtos = leaveService.fuzzySearch(fuzzySearchDto);
        return ResultMap.generate(SUCCESS_CODE,"",leaveRecordDtos);
    }



}
