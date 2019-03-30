package com.xiaogangkui.dao;

import com.xiaogangkui.dto.AttendanceDto;
import com.xiaogangkui.dto.AttendanceReportDto;
import com.xiaogangkui.dto.FuzzySearchDto;
import com.xiaogangkui.entity.Attendance;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Created by luchunyu
 */
public interface AttendanceDao {

    void create(Attendance attendance);

    Attendance findById(@Param("id") int id);

    List<Attendance> fuzzySearch(FuzzySearchDto fuzzySearchDto);

    int fuzzySearchCount(FuzzySearchDto fuzzySearchDto);

    List<Attendance> queryByTime(@Param("userId") int userId,@Param("time") String time);

    List<AttendanceReportDto> groupTotalReport(FuzzySearchDto fuzzySearchDto);
}
