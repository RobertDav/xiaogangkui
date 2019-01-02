package com.xiaogangkui;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Created by luchunyu
 */
@ServletComponentScan
@SpringBootApplication
@ImportResource(locations = {"classpath:conf/*.xml"})
@EnableScheduling
@MapperScan("com.xiaogangkui.dao")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
