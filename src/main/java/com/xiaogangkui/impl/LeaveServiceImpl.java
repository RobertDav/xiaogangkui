package com.xiaogangkui.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.xiaogangkui.dao.LeaveDao;
import com.xiaogangkui.dao.LeaveVerifyRecordDao;
import com.xiaogangkui.dto.FuzzySearchDto;
import com.xiaogangkui.dto.LeaveRecordDto;
import com.xiaogangkui.dto.LeaveVerifyRecordDto;
import com.xiaogangkui.entity.LeaveRecord;
import com.xiaogangkui.entity.LeaveVerifyRecord;
import com.xiaogangkui.service.LeaveService;
import com.xiaogangkui.util.common.BeanMapperUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Created by luchunyu
 */
@Component
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveDao leaveDao;
    @Autowired
    private BeanMapperUtil beanMapperUtil;
    @Autowired
    private LeaveVerifyRecordDao verifyRecordDao;

    @Override
    public void save(LeaveRecordDto leaveRecordDto) {
        LeaveRecord leaveRecord = beanMapperUtil.transForm(leaveRecordDto, LeaveRecord.class);
        leaveRecord.setVerifyInfo( JSON.toJSONString( leaveRecordDto.getVerifyInfos()));
        leaveDao.create(leaveRecord);
    }

    @Override
    public void updateStatus(int id, int status) {
        leaveDao.upadteStatus(id, status);
    }

    @Override
    public List<LeaveRecordDto> fuzzySearch(FuzzySearchDto fuzzySearchDto) {
        List<LeaveRecord> leaveRecords = leaveDao.fuzzySearch(fuzzySearchDto);
        if (CollectionUtils.isEmpty(leaveRecords)) return Lists.newArrayList();
        return beanMapperUtil.batchMapper(leaveRecords, LeaveRecordDto.class);
    }

    @Override
    public LeaveRecordDto findById(int id) {
        LeaveRecordDto leaveRecordDto = beanMapperUtil.transForm(leaveDao.findById(id), LeaveRecordDto.class);
        if(!StringUtils.isEmpty(leaveRecordDto.getVerifyInfo())){
            leaveRecordDto.setVerifyInfos(JSON.parseArray(leaveRecordDto.getVerifyInfo(), LeaveRecordDto.VerifyInfo.class));
        }
        List<LeaveVerifyRecord> leaveVerifyRecords = verifyRecordDao.queryByParentId(id);
        leaveRecordDto.setLeaveVerifyRecordDtos(beanMapperUtil.batchMapper(leaveVerifyRecords,LeaveVerifyRecordDto.class));
        return leaveRecordDto;
    }

    @Override
    public void verifyLeave(LeaveVerifyRecordDto leaveVerifyRecordDto) {
        verifyRecordDao.create(beanMapperUtil.transForm(leaveVerifyRecordDto, LeaveVerifyRecord.class));
        updateStatus(leaveVerifyRecordDto.getLeaveId(),leaveVerifyRecordDto.getStatus());
    }

    @Override
    public List<LeaveRecordDto> queryVerifyList(FuzzySearchDto fuzzySearchDto) {
        List<LeaveRecord> leaveRecords = leaveDao.fuzzySearch(fuzzySearchDto);
        if(CollectionUtils.isEmpty(leaveRecords)) return Lists.newArrayList();
        List<LeaveRecordDto> leaveRecordDtos = beanMapperUtil.batchMapper(leaveRecords, LeaveRecordDto.class);
        List<LeaveRecordDto> collect = leaveRecordDtos.stream()
                .filter(c ->
                        {
                            List<LeaveRecordDto.VerifyInfo> verifyInfos = JSON.parseArray(c.getVerifyInfo(), LeaveRecordDto.VerifyInfo.class);
                            LeaveRecordDto.VerifyInfo verifyInfo = verifyInfos.stream().filter(v -> v.getId() == fuzzySearchDto.getId()).findFirst().orElse(null);
                            if (Objects.nonNull(verifyInfo)) {
                                return true;
                            } else {
                                return false;
                            }
                        }
                ).collect(Collectors.toList());

        return collect;
    }
}
