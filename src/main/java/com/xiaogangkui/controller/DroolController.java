package com.xiaogangkui.controller;

import com.xiaogangkui.dto.MemberInfoDto;
import com.xiaogangkui.dto.RuleDto;
import com.xiaogangkui.entity.Address;
import com.xiaogangkui.entity.AddressCheckResult;
import com.xiaogangkui.util.rule.KieUtils;
import com.xiaogangkui.util.rule.ReloadDroolsRules;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author Created by luchunyu
 */
@RequestMapping("/drool")
@RestController
public class DroolController {


    @Autowired
    private ReloadDroolsRules rules;

    @RequestMapping("/address")
    public String test(){
        KieSession kieSession = KieUtils.getKieSession();
        Address address = new Address();
        address.setPostcode("994251");
        AddressCheckResult result = new AddressCheckResult();
        kieSession.insert(address);
        kieSession.insert(result);
        int ruleFiredCount = kieSession.fireAllRules();
        System.out.println("触发了" + ruleFiredCount + "条规则");
        if(result.isPostCodeResult()){
            System.out.println("规则校验通过");
        }
        return "hhh";
    }

    @RequestMapping(value = "/checkMemberInfo")
    public String checkMemberInfo(){
        KieSession kieSession = KieUtils.getKieSession();
        MemberInfoDto memberInfoDto = MemberInfoDto.builder().gender(1).memberName("拖鞋").memberLevel("牛逼plus").build();
        RuleDto ruleDto = RuleDto.builder().memberCheck(1).build();
        kieSession.insert(memberInfoDto);
        kieSession.insert(ruleDto);
        int count = kieSession.fireAllRules();
        System.out.println("触发了" + count + "条规则");
        return "hello";
    }


    @ResponseBody
    @RequestMapping("/reload")
    public String reload() throws IOException {
        rules.reload();
        return "ok";
    }
}
