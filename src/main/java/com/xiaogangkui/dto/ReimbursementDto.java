package com.xiaogangkui.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Created by luchunyu
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReimbursementDto implements Serializable {
    private int  customerId;
    private int applyer;
    private String applyRemark;
    private Date applyTime;
    private int status;
    private double amount;
    private double unPayAmount;
    private int projectId;
    private String projectName;
    private String applyerName;
    private int applyStatus;
    private String verifyInfo;
    private int id;
    private Date createTime;
    private List<String> certificates;
    private String certificate;
    private List<ReimburseVerifyRecordDto> verifyRecordDtos;

    private List<VerifyInfo> verifyInfoList;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class VerifyInfo{
        private int id;
        private String name;
    }
}
