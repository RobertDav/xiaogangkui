package com.xiaogangkui.dao;

import com.xiaogangkui.dto.FuzzySearchDto;
import com.xiaogangkui.entity.LeaveRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Created by luchunyu
 */
public interface LeaveDao {

    void  create(LeaveRecord leaveRecord);

    void  upadteStatus(@Param("id") int id,@Param("status") int status);

    List<LeaveRecord> fuzzySearch(FuzzySearchDto fuzzySearchDto);

    LeaveRecord findById(@Param("id") int id);
}
