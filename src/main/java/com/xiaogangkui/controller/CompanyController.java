package com.xiaogangkui.controller;

import com.alibaba.fastjson.JSON;
import com.xiaogangkui.dto.CompanyDto;
import com.xiaogangkui.service.CompanyService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Api： 描述 Controller
 * @ApiIgnore： 忽略该 Controller，指不对当前类做扫描
 * @ApiOperation： 描述 Controller类中的 method接口
 * @ApiParam： 单个参数描述，与 @ApiImplicitParam不同的是，他是写在参数左侧的。如（ @ApiParam(name="username",value="用户名")Stringusername）
 * @ApiModel： 描述 POJO对象
 * @ApiProperty： 描述 POJO对象中的属性值
 * @ApiImplicitParam： 描述单个入参信息
 * @ApiImplicitParams： 描述多个入参信息
 * @ApiResponse： 描述单个出参信息
 * @ApiResponses： 描述多个出参信息
 * @ApiError： 接口错误所返回的信息
 * @author Created by luchunyu
 */
@RequestMapping(value = "/company")
@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @ApiOperation(value = "查询")
//    @ApiImplicitParams({@ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String")})
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index() {
        System.out.println("调用company开始");
        List<CompanyDto> companyDtos = companyService.queryAll();
        System.out.println("调用结束");
        return JSON.toJSONString(companyDtos);
    }
}
