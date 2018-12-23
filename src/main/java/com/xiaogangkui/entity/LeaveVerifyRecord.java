package com.xiaogangkui.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Created by luchunyu
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LeaveVerifyRecord extends BaseEntity{
    private int  leaveId ;
    private int actorId;
    private String actorName;
    private int status;
    private String remark;
}
