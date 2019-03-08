package com.xiaogangkui.util.rule;


import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.drools.core.util.IoUtils;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class ReloadDroolsRules {

    public void reload() throws UnsupportedEncodingException {
        KieServices kieServices = KieServices.Factory.get();
        KieFileSystem kfs = kieServices.newKieFileSystem();
        kfs.write("src/main/resources/rules/temp.drl", loadRules());
        KieBuilder kieBuilder = kieServices.newKieBuilder(kfs).buildAll();
        Results results = kieBuilder.getResults();
        if (results.hasMessages(Message.Level.ERROR)) {
            System.out.println(results.getMessages());
            throw new IllegalStateException("### errors ###");
        }

        KieUtils.setKieContainer(kieServices.newKieContainer(getKieServices().getRepository().getDefaultReleaseId()));
        System.out.println("新规则重载成功");
    }

    private String loadRules() {
        // 从数据库加载的规则

        StringBuffer buffer = new StringBuffer();
        try {
            BufferedReader bf = new BufferedReader(new FileReader("C:\\Users\\LuChunYu\\Desktop\\temp.drl"));
            String s = null;
            while((s = bf.readLine())!=null){//使用readLine方法，一次读一行
                buffer.append(s+"\n");
            }
        }catch (Exception e){

        }
        String xml = buffer.toString();
        System.out.println(xml);
        return xml;
//        return "package plausibcheck.adress\n\n import com.xiaogangkui.entity.Address;\n import com.xiaogangkui.entity.AddressCheckResult;\n\n rule \"Postcode 6 numbers\"\n\n    when\n  then\n        System.out.println(\"规则2中打印日志：校验通过!\");\n end";





    }

    private KieServices getKieServices() {
        return KieServices.Factory.get();
    }

}
