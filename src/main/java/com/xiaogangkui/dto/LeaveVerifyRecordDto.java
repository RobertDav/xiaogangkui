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
public class LeaveVerifyRecordDto implements Serializable {
    private int  leaveId ;
    private int actorId;
    private String actorName;
    private int status;
    private String remark;
    private int id;
    private Date createTime;
}
