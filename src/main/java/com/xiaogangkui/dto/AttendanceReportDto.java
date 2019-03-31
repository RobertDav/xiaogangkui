package com.xiaogangkui.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

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

    private int fullAttendance;
    private List<UserDto> fullAttendanceList;
    private List<UserDto> notAttendanceList;
    private List<UserDto> leaveList;
    private int notAttendance;
    private int leaveCount;


}
