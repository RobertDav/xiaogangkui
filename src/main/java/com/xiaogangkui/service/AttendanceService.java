package com.xiaogangkui.service;

import com.xiaogangkui.dto.AttendanceDto;
import com.xiaogangkui.dto.AttendanceReportDto;
import com.xiaogangkui.dto.FuzzySearchDto;

import java.util.List;

/**
 * @author Created by luchunyu
 */
public interface AttendanceService {

    void save(AttendanceDto attendanceDto);

    AttendanceDto findById(int id);

    List<AttendanceDto> fuzzySearch(FuzzySearchDto fuzzySearchDto);

    /**
     * 查询考勤报表
     * @param fuzzySearchDto
     * @return
     */
    AttendanceReportDto totalReport(FuzzySearchDto fuzzySearchDto);

}
