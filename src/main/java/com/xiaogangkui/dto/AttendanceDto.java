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
public class AttendanceDto implements Serializable {
    private int customerId;
    private String userName;
    private int userId;
    private int id;
    private int type;
    private Date createTime;
}
