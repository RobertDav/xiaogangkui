package com.xiaogangkui.controller;

import com.xiaogangkui.dao.CommonDao;
import com.xiaogangkui.dto.CommonDto;
import com.xiaogangkui.dto.FuzzySearchDto;
import com.xiaogangkui.dto.ReportDto;
import com.xiaogangkui.dto.ResultMap;
import com.xiaogangkui.util.common.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

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
        if(Objects.isNull(commonDto)) return ResultMap.generate(ERROR_CODE,"用户名密码不匹配",null);
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

    @RequestMapping(value = "/bidTotal",method = RequestMethod.POST)
    public ResultMap bidTotal(@RequestBody FuzzySearchDto fuzzySearchDto){
        ReportDto reportDto = commonDao.bidTotal(fuzzySearchDto);
        return ResultMap.generate(SUCCESS_CODE,"",reportDto);
    }

    @RequestMapping(value = "/bidTotalByTime",method = RequestMethod.POST)
    public ResultMap bidTotalByTime(@RequestBody FuzzySearchDto fuzzySearchDto){
        ReportDto reportDto = commonDao.bidTotalByTime(fuzzySearchDto);
        ReportDto reportDto1 = commonDao.bidCountByTime(fuzzySearchDto);
        fuzzySearchDto.setStatus(4);
        ReportDto reportDto2 = commonDao.bidCountByTime(fuzzySearchDto);
        fuzzySearchDto.setStatus(2);
        ReportDto reportDto3 = commonDao.bidCountByTime(fuzzySearchDto);
        reportDto.setPartMakeBidCount(reportDto1.getPartCount());
        reportDto.setPartBidCount(reportDto1.getPartCount() - reportDto2.getPartCount());
        reportDto.setPartHitBidCount(reportDto3.getPartCount());
        reportDto.setPartBidCount(reportDto1.getPartMakeBidCount());
        return ResultMap.generate(SUCCESS_CODE,"",reportDto);
    }

    @RequestMapping(value = "/contractTotal",method = RequestMethod.POST)
    public ResultMap contractTotal(@RequestBody FuzzySearchDto fuzzySearchDto){
        ReportDto reportDto = commonDao.contractTotal(fuzzySearchDto);
        fuzzySearchDto.setStartTime(null);
        fuzzySearchDto.setEndTime(null);
        ReportDto reportDto1 = commonDao.contractTotal(fuzzySearchDto);
        reportDto.setContractMonthAdd(reportDto1.getContractTotal());
        return ResultMap.generate(SUCCESS_CODE,"",reportDto);
    }

    @RequestMapping(value = "/contractTotalByTime",method = RequestMethod.POST)
    public ResultMap contractTotalByTime(@RequestBody FuzzySearchDto fuzzySearchDto){
        fuzzySearchDto.setFinanceType(1);
        ReportDto reportDto = commonDao.contractTotalByTime(fuzzySearchDto);
        reportDto.setPartCollectContractTotal(reportDto.getPartContractTotal());
        reportDto.setPartCollectAmount(reportDto.getPartAmount());
        fuzzySearchDto.setFinanceType(2);

        ReportDto reportDto1 = commonDao.contractTotalByTime(fuzzySearchDto);
        reportDto.setPartPayContractTotal(reportDto1.getPartContractTotal());
        reportDto.setPartPayAmount(reportDto1.getPartAmount());

        fuzzySearchDto.setPayType(1);
        ReportDto reportDto2 = commonDao.contractRecordCountByTime(fuzzySearchDto);
        reportDto.setPartPayRecordAmount(reportDto2.getPayAmount());

        fuzzySearchDto.setPayType(2);
        ReportDto reportDto3 = commonDao.contractRecordCountByTime(fuzzySearchDto);
        reportDto.setPartCollectRecordAmount(reportDto3.getPayAmount());
        return ResultMap.generate(SUCCESS_CODE,"",reportDto);
    }


}
