package com.xiaogangkui.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Created by luchunyu
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reimbursement extends BaseEntity {
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
    private String certificate;
}
