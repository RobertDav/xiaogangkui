package com.xiaogangkui.dto;

import com.xiaogangkui.entity.LeaveRecord;
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
public class LeaveRecordDto implements Serializable{
    private int id;
    private int applyerId;
    private String applyerName;
    private int type;
    private Date startTime;
    private Date endTime;
    private String remark;
    private String verifyInfo;
    private int status;
    private Date createTime;
    private int customerId;

    private List<LeaveRecordDto.VerifyInfo> verifyInfos;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static  class  VerifyInfo{
        private int id;
        private String name;
    }
}
