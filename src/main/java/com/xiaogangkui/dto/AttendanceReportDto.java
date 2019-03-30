package com.xiaogangkui.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceReportDto implements Serializable {
    private int total;
    private int userId;
    private String userName;
    private int type;
    private String createTime;
}
