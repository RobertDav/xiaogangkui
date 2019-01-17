package com.xiaogangkui.controller;

import com.xiaogangkui.entity.Address;
import com.xiaogangkui.entity.AddressCheckResult;
import com.xiaogangkui.util.rule.KieUtils;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Created by luchunyu
 */
@RequestMapping("/test")
@Controller
public class TestController {

    @Autowired
    private KieSession kieSession;

    @ResponseBody
    @RequestMapping("/address")
    public void test(){
        KieSession kieSession = KieUtils.getKieContainer().newKieSession();

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

        kieSession.dispose();

    }

}
