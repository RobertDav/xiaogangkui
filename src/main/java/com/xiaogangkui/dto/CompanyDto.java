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
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto  implements Serializable {
    private int id;
    private String companyName;
    private Date createTime;
    private Date updateTime;
    private int isDeleted;
}
