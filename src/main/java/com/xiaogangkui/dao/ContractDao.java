package com.xiaogangkui.dao;

import com.xiaogangkui.dto.ContractApproveDto;
import com.xiaogangkui.dto.ContractDto;
import com.xiaogangkui.dto.ContractFoceDto;
import com.xiaogangkui.dto.FuzzySearchDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

    @Select("select * from ts_contract_force_record where contactId = #{contactId} and parentId != 0 ")
    List<ContractFoceDto> queryForceList(@Param("contactId") int contactId);
}
