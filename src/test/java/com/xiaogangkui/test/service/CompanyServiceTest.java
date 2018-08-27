package com.xiaogangkui.test.service;

import com.xiaogangkui.dto.CompanyDto;
import com.xiaogangkui.service.CompanyService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Created by luchunyu
 */
public class CompanyServiceTest extends BaseTest {

    @Autowired
    private CompanyService companyService;

    @Test
    public void testQueryAll(){
        List<CompanyDto> companyDtos = companyService.queryAll();
        System.out.println(companyDtos);
    }
}
