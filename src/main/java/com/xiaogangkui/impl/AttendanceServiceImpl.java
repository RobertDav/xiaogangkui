package com.xiaogangkui.impl;

import com.xiaogangkui.dao.AttendanceDao;
import com.xiaogangkui.dto.AttendanceDto;
import com.xiaogangkui.dto.FuzzySearchDto;
import com.xiaogangkui.entity.Attendance;
import com.xiaogangkui.service.AttendanceService;
import com.xiaogangkui.util.common.BeanMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Created by luchunyu
 */
@Component
public class AttendanceServiceImpl implements AttendanceService {
    @Autowired
    private AttendanceDao attendanceDao;
    @Autowired
    private BeanMapperUtil beanMapperUtil;
    @Override
    public void save(AttendanceDto attendanceDto) {
        attendanceDao.create(beanMapperUtil.transForm(attendanceDto, Attendance.class));
    }

    @Override
    public AttendanceDto findById(int id) {
        return beanMapperUtil.transForm( attendanceDao.findById(id),AttendanceDto.class);
    }

    @Override
    public List<AttendanceDto> fuzzySearch(FuzzySearchDto fuzzySearchDto) {
        List<Attendance> attendances = attendanceDao.fuzzySearch(fuzzySearchDto);
        return beanMapperUtil.batchMapper(attendances,AttendanceDto.class);
    }
}
