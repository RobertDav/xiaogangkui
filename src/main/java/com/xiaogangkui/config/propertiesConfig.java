package com.xiaogangkui.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Created by luchunyu
 */
@Configuration
@PropertySource(value ={"classpath:dubbo.properties"})
public class propertiesConfig {
}
