package com.xiaogangkui.dao;

import com.xiaogangkui.dto.FuzzySearchDto;
import com.xiaogangkui.entity.Reimbursement;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Created by luchunyu
 */
public interface ReimbursementDao {

    void  create(Reimbursement reimbursement);

    Reimbursement findById(@Param("id") int id);

    void updateStatus(@Param("id") int id,@Param("applyStatus") int applyStatus);

    List<Reimbursement> fuzzySearch(FuzzySearchDto fuzzySearchDto);
}
