package com.xiaogangkui.dao;

import com.xiaogangkui.entity.ReimburseVerifyRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Created by luchunyu
 */
@Mapper
public interface ReimburseVerifyRecordDao {

    void create(ReimburseVerifyRecord reimburseVerifyRecord);

    ReimburseVerifyRecord findById(@Param("id") int id);

    List<ReimburseVerifyRecord> queryByParentId(@Param("parentId") int parentId);
}
