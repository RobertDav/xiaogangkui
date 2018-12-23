package com.xiaogangkui.controller;

import com.xiaogangkui.dto.*;
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

    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    public ResultMap verify(@RequestBody ReimburseVerifyRecordDto param) {
        reimbursementService.verify(param);
        return ResultMap.generate(SUCCESS_CODE);
    }
    @RequestMapping(value = "/queryVerifyList",method = RequestMethod.POST)
    public ResultMap queryVerifyList(@RequestBody FuzzySearchDto fuzzySearchDto){
        List<ReimbursementDto> reimbursementDtos = reimbursementService.queryVerifyList(fuzzySearchDto);
        return ResultMap.generate(SUCCESS_CODE,"",reimbursementDtos);
    }

}
