package com.xiaogangkui.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.xiaogangkui.dao.ReimburseVerifyRecordDao;
import com.xiaogangkui.dao.ReimbursementDao;
import com.xiaogangkui.dto.FuzzySearchDto;
import com.xiaogangkui.dto.ReimburseVerifyRecordDto;
import com.xiaogangkui.dto.ReimbursementDto;
import com.xiaogangkui.entity.ReimburseVerifyRecord;
import com.xiaogangkui.entity.Reimbursement;
import com.xiaogangkui.service.ReimbursementService;
import com.xiaogangkui.util.common.BeanMapperUtil;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Created by luchunyu
 */
@Component
public class ReimbursementServiceImpl implements ReimbursementService {
    @Autowired
    private ReimbursementDao reimbursementDao;
    @Autowired
    private BeanMapperUtil beanMapperUtil;
    @Autowired
    private ReimburseVerifyRecordDao reimburseVerifyRecordDao;

    @Override
    public void save(ReimbursementDto reimbursementDto) {
        Reimbursement reimbursement = beanMapperUtil.transForm(reimbursementDto, Reimbursement.class);
        reimbursement.setVerifyInfo(JSON.toJSONString(reimbursementDto.getVerifyInfoList()));
        reimbursement.setCertificate(JSON.toJSONString(reimbursementDto.getCertificates()));
        reimbursementDao.create(reimbursement);
    }

    @Override
    public ReimbursementDto load(int id) {
        Reimbursement reimbursement = reimbursementDao.findById(id);
        ReimbursementDto reimbursementDto = beanMapperUtil.transForm(reimbursement, ReimbursementDto.class);
        if(!StringUtils.isEmpty(reimbursement.getVerifyInfo())){
            reimbursementDto.setVerifyInfoList(JSON.parseArray(reimbursementDto.getVerifyInfo(), ReimbursementDto.VerifyInfo.class));
        }
        if(!StringUtils.isEmpty(reimbursement.getCertificate())){
            reimbursementDto.setCertificates(JSON.parseArray(reimbursementDto.getCertificate(), String.class));
        }
        List<ReimburseVerifyRecord> reimburseVerifyRecords = reimburseVerifyRecordDao.queryByParentId(id);
        List<ReimburseVerifyRecordDto> reimburseVerifyRecordDtos = beanMapperUtil.batchMapper(reimburseVerifyRecords, ReimburseVerifyRecordDto.class);
        reimbursementDto.setVerifyRecordDtos(reimburseVerifyRecordDtos);
        return reimbursementDto;
    }



    @Override
    public void verify(ReimburseVerifyRecordDto reimburseVerifyRecordDto) {
        reimburseVerifyRecordDao.create(beanMapperUtil.transForm(reimburseVerifyRecordDto, ReimburseVerifyRecord.class));
        // 判断是否是最后一次审核通过
        ReimbursementDto reimbursementDto = load(reimburseVerifyRecordDto.getReimburseId());
        if(!CollectionUtils.isEmpty(reimbursementDto.getVerifyInfoList())){
            if(reimbursementDto.getVerifyInfoList().get(reimbursementDto.getVerifyInfoList().size()-1).getId() == reimburseVerifyRecordDto.getActorId()
             && reimburseVerifyRecordDto.getStatus() == 1){
                reimbursementDao.updateStatus(reimburseVerifyRecordDto.getReimburseId(),reimburseVerifyRecordDto.getStatus());
            }else if (reimburseVerifyRecordDto.getStatus() == 2){
                reimbursementDao.updateStatus(reimburseVerifyRecordDto.getReimburseId(),reimburseVerifyRecordDto.getStatus());
            }
        }

    }

    @Override
    public List<ReimbursementDto> queryVerifyList(FuzzySearchDto fuzzySearchDto) {
        List<Reimbursement> reimbursements = reimbursementDao.fuzzySearch(fuzzySearchDto);
        if(CollectionUtils.isEmpty(reimbursements)) return Lists.newArrayList();
        List<Reimbursement> collect = reimbursements.stream().filter(
                c -> {
                    if(StringUtils.isEmpty(c.getVerifyInfo()) || c.getVerifyInfo().equals("null")){
                        return false;
                    }else {
                        if(fuzzySearchDto.getStatus() == 0){
                            ReimburseVerifyRecord reimburseInfo = reimburseVerifyRecordDao.findByActorIdAndReimburseId(fuzzySearchDto.getId(), c.getId());
                            if(Objects.nonNull(reimburseInfo)) return false;

                        }
                        List<ReimbursementDto.VerifyInfo> verifyInfos = JSON.parseArray(c.getVerifyInfo(), ReimbursementDto.VerifyInfo.class);
                        ReimbursementDto.VerifyInfo verifyInfo1 = verifyInfos.stream().filter(verifyInfo -> verifyInfo.getId() == fuzzySearchDto.getId()).findFirst().orElse(null);
                        //当需要用户审核的时候 判断这个用户是否有审核资格
                        if (Objects.nonNull(verifyInfo1)) {
                            return true;
                        } else {
                            return false;
                        }
                    }

                }
        ).collect(Collectors.toList());
        return beanMapperUtil.batchMapper(collect,ReimbursementDto.class);
    }

    @Override
    public List<ReimbursementDto> queryList(FuzzySearchDto fuzzySearchDto) {
        List<Reimbursement> reimbursements = reimbursementDao.fuzzySearch(fuzzySearchDto);
        if(CollectionUtils.isEmpty(reimbursements)) return Lists.newArrayList();
        return beanMapperUtil.batchMapper(reimbursements,ReimbursementDto.class);
    }


}
