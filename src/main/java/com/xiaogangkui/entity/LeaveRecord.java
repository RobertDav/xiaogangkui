package com.xiaogangkui.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author Created by luchunyu
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LeaveRecord extends BaseEntity {
    private int applyerId;
    private String applyerName;
    private int type;
    private Date startTime;
    private Date endTime;
    private String remark;
    private String verifyInfo;
    private int status;
    private int customerId;
}
