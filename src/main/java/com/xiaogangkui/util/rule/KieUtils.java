package com.xiaogangkui.util.rule;

import org.kie.api.runtime.KieContainer;

/**
 * @author Created by luchunyu
 */
public class KieUtils {

    private static KieContainer kieContainer;

    public static KieContainer getKieContainer() {
        return kieContainer;
    }

    public static void setKieContainer(KieContainer kieContainer) {
        KieUtils.kieContainer = kieContainer;
    }
}
