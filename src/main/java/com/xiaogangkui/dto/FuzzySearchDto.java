package com.xiaogangkui.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Created by luchunyu
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FuzzySearchDto implements Serializable {
    private int id;
    private int status;
    private Integer leaveStatus;
    private Integer reimburseStatus;
    private int applyerId;
    private int applyer;
    private int customerId;
    private int applyStatus;
    private int userId;
    private Date startTime;
    private Date endTime;
    private int start;
    private int end;
    private String account;
    private String password;
    private String newPassword;
    private int limit;
    private int collectStatus;
    private String time;
}
