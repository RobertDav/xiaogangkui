package com.xiaogangkui.impl;

import com.xiaogangkui.dao.AttendanceDao;
import com.xiaogangkui.dto.AttendanceDto;
import com.xiaogangkui.dto.FuzzySearchDto;
import com.xiaogangkui.entity.Attendance;
import com.xiaogangkui.service.AttendanceService;
import com.xiaogangkui.util.common.ApplicationException;
import com.xiaogangkui.util.common.BeanMapperUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
        //查询用户是否已经打过卡
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String time = format.format(attendanceDto.getCreateTime());
        List<Attendance> attendances = attendanceDao.queryByTime(attendanceDto.getUserId(), time);
        if(CollectionUtils.isNotEmpty(attendances)){
            List<Attendance> collect = attendances.stream().filter(attendance -> attendance.getType() == attendanceDto.getType())
                    .filter(attendance -> (attendanceDto.getType() == 3 && attendance.getType() == 1))
                    .filter(attendance -> (attendanceDto.getType() == 4 && attendance.getType() == 2))
                    .collect(Collectors.toList());
            if(CollectionUtils.isNotEmpty(collect)){
                throw  new ApplicationException("请不要重复打卡");
            }
        }
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
