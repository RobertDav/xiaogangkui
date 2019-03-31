package com.xiaogangkui.dao;

import com.xiaogangkui.dto.CommonDto;
import com.xiaogangkui.dto.FuzzySearchDto;
import com.xiaogangkui.dto.ReportDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author Created by luchunyu
 */
public interface CommonDao {

    List<CommonDto> queryAllProject(FuzzySearchDto fuzzySearchDto);

    List<CommonDto> queryAllUser(FuzzySearchDto fuzzySearchDto);

    CommonDto loadByAccount(FuzzySearchDto fuzzySearchDto);

    CommonDto loadByUserId(FuzzySearchDto fuzzySearchDto);
    @Update("update t_user set password = #{password} where id = #{id }")
    void updateUserPassword(@Param("id") int id,@Param("password") String password);

    ReportDto bidTotal(FuzzySearchDto fuzzySearchDto);

    ReportDto bidTotalByTime(FuzzySearchDto fuzzySearchDto);

    ReportDto bidCountByTime(FuzzySearchDto fuzzySearchDto);


    ReportDto contractTotal(FuzzySearchDto fuzzySearchDto);

    ReportDto contractTotalByTime(FuzzySearchDto fuzzySearchDto);

    ReportDto contractRecordCountByTime(FuzzySearchDto fuzzySearchDto);


}
