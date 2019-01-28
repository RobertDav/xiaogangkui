package com.xiaogangkui.util.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanRegisterConfig {

    @Bean
    public Logger logger(){
        Logger logger = LoggerFactory.getLogger(getClass());
        return logger;
    }

}
