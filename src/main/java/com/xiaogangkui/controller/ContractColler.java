package com.xiaogangkui.controller;

import com.alibaba.fastjson.JSON;
import com.xiaogangkui.dao.ContractDao;
import com.xiaogangkui.dto.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.xiaogangkui.dto.ResultMap.SUCCESS_CODE;

/**
 * @author Created by luchunyu
 */
@RequestMapping(value = "/contract")
@RestController
public class ContractColler {

    @Autowired
    private ContractDao contractDao;

    @RequestMapping(value = "/queryList",method = RequestMethod.POST)
    public ResultMap queryList(@RequestBody FuzzySearchDto fuzzySearchDto){
        List<ContractDto> contractDtos = contractDao.queryList(fuzzySearchDto);
        if(CollectionUtils.isNotEmpty(contractDtos)){
            List<ContractDto> collect = contractDtos.stream().filter(contractDto -> {
                List<Integer> integers = JSON.parseArray(contractDto.getApplyers(), Integer.class);
                if (integers.contains(fuzzySearchDto.getId())) {
                    return true;
                } else {
                    return false;
                }
            }).collect(Collectors.toList());
            Map<String,Object> map = new HashMap<>();
            map.put("total",collect.size());
            map.put("list",collect.subList(fuzzySearchDto.getStart(),collect.size() >fuzzySearchDto.getEnd()?fuzzySearchDto.getEnd():collect.size()));
            return ResultMap.generate(SUCCESS_CODE,"",map);
        }
        return ResultMap.generate(SUCCESS_CODE,"",null);

    }

    @RequestMapping(value = "/findById",method = RequestMethod.POST)
    public ResultMap findById(@RequestBody FuzzySearchDto fuzzySearchDto){
        ContractDto contractDto = contractDao.findById(fuzzySearchDto.getId());
        List<ContractApproveDto> contractApproveDtos = contractDao.queryApproveList(fuzzySearchDto.getId());
        if(CollectionUtils.isNotEmpty(contractApproveDtos)){
            contractDto.setApproveDtos(contractApproveDtos);
        }
        return ResultMap.generate(SUCCESS_CODE,"",contractDto);
    }

    @RequestMapping(value = "/saveApprove",method = RequestMethod.POST)
    public ResultMap saveApprove(@RequestBody ContractApproveDto approveDto){
        contractDao.saveApprove(approveDto);
        contractDao.updateStatus(approveDto.getContactId(),approveDto.getStatus());
        return ResultMap.generate(SUCCESS_CODE,"");
    }

    @RequestMapping(value = "/queryForceList",method = RequestMethod.POST)
    public ResultMap queryForceList(@RequestBody FuzzySearchDto fuzzySearchDto){
        List<ContractDto> contractDtos = contractDao.queryForceList(fuzzySearchDto);
        if(CollectionUtils.isNotEmpty(contractDtos)){
            List<ContractDto> collect = contractDtos.stream().filter(contractDto -> {
                List<Integer> integers = JSON.parseArray(contractDto.getReviewIds(), Integer.class);
                if (integers.contains(fuzzySearchDto.getId())) {
                    return true;
                } else {
                    return false;
                }
            }).collect(Collectors.toList());
            Map<String,Object> map = new HashMap<>();
            map.put("total",collect.size());
            map.put("list",collect.subList(fuzzySearchDto.getStart(),collect.size() >fuzzySearchDto.getEnd()?fuzzySearchDto.getEnd():collect.size()));
            return ResultMap.generate(SUCCESS_CODE,"",map);
        }
        return ResultMap.generate(SUCCESS_CODE,"",null);
    }

    @RequestMapping(value = "/loadForceDetail",method = RequestMethod.POST)
    public ResultMap loadForceDetail(@RequestBody FuzzySearchDto fuzzySearchDto){
        ContractDto contractDto = contractDao.findById(fuzzySearchDto.getId());
        List<ContractFoceDto> contractFoceDtos = contractDao.queryForceListById(fuzzySearchDto.getId());
        contractDto.setContractFoceDtos(contractFoceDtos);
        return ResultMap.generate(SUCCESS_CODE,"",contractDto);
    }
    @RequestMapping(value = "/loadForceDetail",method = RequestMethod.POST)
    public ResultMap saveForce(@RequestBody ContractFoceDto contractFoceDto){
        contractDao.save(contractFoceDto);
        int collectStatus = 0;
        if(contractFoceDto.getStatus() == 1){
            collectStatus = 4;
        }
        if(contractFoceDto.getStatus() == 2){
            collectStatus = 2;
        }
        contractDao.updateCollectStatus(contractFoceDto.getContactId(),collectStatus);
        return ResultMap.generate(SUCCESS_CODE,"");
    }
}
