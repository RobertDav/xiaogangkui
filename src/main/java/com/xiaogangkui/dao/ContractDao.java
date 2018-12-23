package com.xiaogangkui.dao;

import com.xiaogangkui.dto.ContractDto;
import com.xiaogangkui.dto.FuzzySearchDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Created by luchunyu
 */
@Mapper
public interface ContractDao {

    List<ContractDto> queryList(FuzzySearchDto fuzzySearchDto);

    ContractDto findById(@Param("id") int id);
}
