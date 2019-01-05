package com.xiaogangkui.dao;

import com.xiaogangkui.dto.ContractApproveDto;
import com.xiaogangkui.dto.ContractDto;
import com.xiaogangkui.dto.ContractFoceDto;
import com.xiaogangkui.dto.FuzzySearchDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author Created by luchunyu
 */
public interface ContractDao {

    List<ContractDto> queryList(FuzzySearchDto fuzzySearchDto);

    ContractDto findById(@Param("id") int id);

    List<ContractApproveDto> queryApproveList(@Param("contactId") int contactId);

    void saveApprove(ContractApproveDto contractApproveDto);

    void updateStatus(@Param("id") int id,@Param("status") int status);


    List<ContractDto> queryForceList(FuzzySearchDto fuzzySearchDto);

    List<ContractFoceDto> queryForceListById(@Param("contactId") int contactId);

    void save(ContractFoceDto contractFoceDto);

    @Update("update t_contract set collectStatus = #{collectStatus} where id = #{id}")
    void updateCollectStatus(@Param("id") int id,@Param("collectStatus") int collectStatus);
}
