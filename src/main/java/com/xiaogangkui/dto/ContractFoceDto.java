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
public class ContractFoceDto implements Serializable{
    private int id;
    private String reviewIds;
    private int actor;
    private String status;
    private Date createTime;
    private Date updateTime;
    private int isDeleted;
    private int contactId;
    private int customerId;
    private int  parentId;
    private String actorName;
    private String remark;

}
