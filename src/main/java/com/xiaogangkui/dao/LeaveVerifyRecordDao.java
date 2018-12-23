package com.xiaogangkui.dao;

import com.xiaogangkui.entity.LeaveVerifyRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Created by luchunyu
 */
@Mapper
public interface LeaveVerifyRecordDao {

    void create(LeaveVerifyRecord leaveVerifyRecord);

    LeaveVerifyRecord findById(@Param("id") int id);
}
