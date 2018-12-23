package com.xiaogangkui.dao;

import com.xiaogangkui.entity.LeaveVerifyRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Created by luchunyu
 */
@Mapper
public interface LeaveVerifyRecordDao {

    void create(LeaveVerifyRecord leaveVerifyRecord);

    LeaveVerifyRecord findById(@Param("id") int id);

    List<LeaveVerifyRecord> queryByParentId(@Param("parentId") int parentId);
}
