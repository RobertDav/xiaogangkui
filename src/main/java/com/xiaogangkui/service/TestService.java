package com.xiaogangkui.service;

import com.xiaogangkui.dto.CompanyDto;

public interface TestService {

    String loadInfo(String key);

    String testLoad();

    CompanyDto redisTest(CompanyDto companyDto);

    Integer redisTest(CompanyDto companyDto ,String name);
}
