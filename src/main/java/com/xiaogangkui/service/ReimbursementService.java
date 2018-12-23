package com.xiaogangkui.service;

import com.xiaogangkui.dto.FuzzySearchDto;
import com.xiaogangkui.dto.ReimburseVerifyRecordDto;
import com.xiaogangkui.dto.ReimbursementDto;

import java.util.List;

/**
 * @author Created by luchunyu
 */
public interface ReimbursementService {

    void save(ReimbursementDto reimbursementDto);

    ReimbursementDto load(int id);


    void verify(ReimburseVerifyRecordDto reimburseVerifyRecordDto);

    List<ReimbursementDto> queryVerifyList(FuzzySearchDto fuzzySearchDto);
}
