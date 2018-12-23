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
public class ContractApproveDto implements Serializable{
    private int id;
    private int customerId;
    private int contactId;
    private int contractName;
    private int approveId;
    private String approveName;
    private int status;
    private Date createTime;
    private int isDeleted;
    private String remark;
}
