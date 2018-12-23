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
public class ReimburseVerifyRecord extends BaseEntity{
    private int  actorId;
    private String actorName;
    private int status;
    private Date createTime;
    private int isDeleted;
    private String remark;
    private int reimburseId;
}
