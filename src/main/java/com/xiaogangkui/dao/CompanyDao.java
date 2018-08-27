package com.xiaogangkui.dao;

import com.xiaogangkui.entity.Company;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Created by luchunyu
 */
@Mapper
public interface CompanyDao {

    List<Company> queryAll();
}
