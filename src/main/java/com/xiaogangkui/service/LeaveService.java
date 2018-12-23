package com.xiaogangkui.service;

import com.xiaogangkui.dto.FuzzySearchDto;
import com.xiaogangkui.dto.LeaveRecordDto;
import com.xiaogangkui.dto.LeaveVerifyRecordDto;

import java.util.List;

/**
 * @author Created by luchunyu
 */
public interface LeaveService {

    void save(LeaveRecordDto leaveRecordDto);

    void updateStatus(int id ,int status);

    List<LeaveRecordDto> fuzzySearch(FuzzySearchDto fuzzySearchDto);

    LeaveRecordDto findById(int id);

    void verifyLeave(LeaveVerifyRecordDto leaveVerifyRecordDto);

    List<LeaveRecordDto> queryVerifyList(FuzzySearchDto fuzzySearchDto);
}
