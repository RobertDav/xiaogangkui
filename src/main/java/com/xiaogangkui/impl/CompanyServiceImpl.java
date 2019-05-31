package com.xiaogangkui.impl;

import com.xiaogangkui.dao.CompanyDao;
import com.xiaogangkui.dto.CompanyDto;
import com.xiaogangkui.entity.Company;
import com.xiaogangkui.service.CompanyService;
import com.xiaogangkui.util.common.BeanMapperUtil;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Created by luchunyu
 */
@Component
@EnableAspectJAutoProxy
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private BeanMapperUtil beanMapperUtil;
    @Override
    public List<CompanyDto> queryAll() {

        List<Company> companies = companyDao.queryAll();
        return beanMapperUtil.batchMapper(companies,CompanyDto.class);
    }

}
