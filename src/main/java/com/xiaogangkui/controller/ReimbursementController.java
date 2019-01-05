package com.xiaogangkui.controller;

import com.google.common.collect.Lists;
import com.xiaogangkui.dto.*;
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
@RequestMapping(value = "/reimbursement")
@RestController
public class ReimbursementController {

    @Autowired
    private ReimbursementService reimbursementService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResultMap save(@RequestBody ReimbursementDto reimbursementDto) {
        reimbursementService.save(reimbursementDto);
        return ResultMap.generate(SUCCESS_CODE);
    }
    @RequestMapping(value = "/load", method = RequestMethod.POST)
    public ResultMap load(@RequestBody ReimbursementDto param) {
        ReimbursementDto reimbursementDto = reimbursementService.load(param.getId());
        return ResultMap.generate(SUCCESS_CODE,"",reimbursementDto);
    }


    @RequestMapping(value = "/queryList", method = RequestMethod.POST)
    public ResultMap queryList(@RequestBody FuzzySearchDto fuzzySearchDto){
        List<ReimbursementDto> reimbursementDtos = reimbursementService.queryList(fuzzySearchDto);
        List<ReimbursementDto> result = Lists.newArrayList();
        if(CollectionUtils.isNotEmpty(reimbursementDtos)){
            result = reimbursementDtos.subList(fuzzySearchDto.getStart(), reimbursementDtos.size() > fuzzySearchDto.getEnd() ? fuzzySearchDto.getEnd() : reimbursementDtos.size());
        }
        Map<String,Object> map = new HashMap<>();
        map.put("total",reimbursementDtos.size());
        map.put("list",result);
        return ResultMap.generate(SUCCESS_CODE,"",reimbursementDtos);
    }
    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    public ResultMap verify(@RequestBody ReimburseVerifyRecordDto param) {
        reimbursementService.verify(param);
        return ResultMap.generate(SUCCESS_CODE);
    }
    @RequestMapping(value = "/queryVerifyList",method = RequestMethod.POST)
    public ResultMap queryVerifyList(@RequestBody FuzzySearchDto fuzzySearchDto){
        List<ReimbursementDto> reimbursementDtos = reimbursementService.queryVerifyList(fuzzySearchDto);
        List<ReimbursementDto> result = Lists.newArrayList();
        if(CollectionUtils.isNotEmpty(reimbursementDtos)){
            result = reimbursementDtos.subList(fuzzySearchDto.getStart(), reimbursementDtos.size() > fuzzySearchDto.getEnd() ? fuzzySearchDto.getEnd() : reimbursementDtos.size());
        }
        Map<String,Object> map = new HashMap<>();
        map.put("total",reimbursementDtos.size());
        map.put("list",result);
        return ResultMap.generate(SUCCESS_CODE,"",map);
    }

}
