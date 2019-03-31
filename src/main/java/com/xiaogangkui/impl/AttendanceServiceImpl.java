package com.xiaogangkui.impl;

import com.google.common.collect.Lists;
import com.xiaogangkui.dao.AttendanceDao;
import com.xiaogangkui.dao.CommonDao;
import com.xiaogangkui.dto.*;
import com.xiaogangkui.entity.Attendance;
import com.xiaogangkui.service.AttendanceService;
import com.xiaogangkui.service.LeaveService;
import com.xiaogangkui.util.common.ApplicationException;
import com.xiaogangkui.util.common.BeanMapperUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
    @Autowired
    private CommonDao commonDao;
    @Autowired
    private LeaveService leaveService;
    @Override
    public void save(AttendanceDto attendanceDto) {
        //查询用户是否已经打过卡
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String time = format.format(attendanceDto.getCreateTime());
        List<Attendance> attendances = attendanceDao.queryByTime(attendanceDto.getUserId(), time);
        if(CollectionUtils.isNotEmpty(attendances)){
            List<Attendance> collect = Lists.newArrayList();
            for (Attendance attendance : attendances) {
                if(attendance.getType() == attendanceDto.getType()){
                    collect.add(attendance);
                }
                if(attendanceDto.getType() == 3 &&  attendance.getType() == 1){
                    collect.add(attendance);
                }
                if(attendanceDto.getType() == 4 &&  attendance.getType() == 2){
                    collect.add(attendance);
                }
            }


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

    @Override
    public AttendanceReportDto totalReport(FuzzySearchDto fuzzySearchDto) {
        fuzzySearchDto.setEndTime(getEndTime(fuzzySearchDto.getEndTime()));
        List<CommonDto> users = commonDao.queryAllUser(fuzzySearchDto);
        List<AttendanceReportDto> reportDtos = attendanceDao.groupTotalReport(fuzzySearchDto);
        fuzzySearchDto.setStatus(1);
        AttendanceReportDto returnReportDto = AttendanceReportDto.builder().build();
        int fullAttendance = 0;
        int notAttendance = 0;
        int leaveCount = 0;
        if(CollectionUtils.isNotEmpty(reportDtos)){
            Map<Integer, List<AttendanceReportDto>> collect = reportDtos.stream().collect(Collectors.groupingBy(AttendanceReportDto::getUserId));
            long diffDay = getDiffDay(fuzzySearchDto.getStartTime(),fuzzySearchDto.getEndTime());
            List<Integer> userIds = Lists.newArrayList();
            for (Integer usrId : collect.keySet()) {
                List<AttendanceReportDto> attendanceReportDtos = collect.get(usrId);
                if(attendanceReportDtos.size() >= diffDay * 2){
                    fullAttendance ++;
                    userIds.add(usrId);
                }
            }
            List<UserDto> fullAttendanceList = users.stream().filter(c -> userIds.contains(c.getId())).map(c ->
                    UserDto.builder().id(c.getId()).name(c.getName()).build()
            ).collect(Collectors.toList());
            List<UserDto> notAttendanceList = users.stream().filter(c -> !userIds.contains(c.getId())).map(c ->
                    UserDto.builder().id(c.getId()).name(c.getName()).build()
            ).collect(Collectors.toList());
            returnReportDto.setFullAttendanceList(fullAttendanceList);
            returnReportDto.setNotAttendanceList(notAttendanceList);
            List<LeaveRecordDto> leaveRecordDtos = leaveService.fuzzySearch(fuzzySearchDto);
            if(CollectionUtils.isNotEmpty(leaveRecordDtos)){
                Map<Integer, List<LeaveRecordDto>> leaveCollect = leaveRecordDtos.stream().collect(Collectors.groupingBy(LeaveRecordDto::getApplyerId));
                leaveCount = leaveCollect.keySet().size();
                List<UserDto> leaveList = users.stream().filter(c -> !leaveCollect.keySet().contains(c.getId())).map(c ->
                        UserDto.builder().id(c.getId()).name(c.getName()).build()
                ).collect(Collectors.toList());
                returnReportDto.setLeaveList(leaveList);
            }
            returnReportDto.setLeaveCount(leaveCount);
            notAttendance =  users.size() - fullAttendance;
            returnReportDto.setNotAttendance(notAttendance);
            returnReportDto.setFullAttendance(fullAttendance);
        }
        //计算考勤人数
        return returnReportDto;
    }

    public long getDiffDay(Date start, Date  end){
        start = getStartTime(start);
        end =  getStartTime(end);
        return  (start.getTime()-end.getTime()+1000000)/(60*60*24*1000);
    }

    public static Date getStartTime(Date date) {
        Calendar dateStart = Calendar.getInstance();
        dateStart.setTime(date);
        dateStart.set(Calendar.HOUR_OF_DAY, 0);
        dateStart.set(Calendar.MINUTE, 0);
        dateStart.set(Calendar.SECOND, 0);
        dateStart.set(Calendar.MILLISECOND, 0);
        return dateStart.getTime();
    }
    public static Date getEndTime(Date date) {
        Calendar dateEnd = Calendar.getInstance();
        dateEnd.setTime(date);
        dateEnd.set(Calendar.HOUR_OF_DAY, 23);
        dateEnd.set(Calendar.MINUTE, 59);
        dateEnd.set(Calendar.SECOND, 59);
        dateEnd.set(Calendar.MILLISECOND, 999);
        return dateEnd.getTime();
    }


}
