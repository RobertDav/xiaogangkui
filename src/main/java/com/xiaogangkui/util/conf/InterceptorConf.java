package com.xiaogangkui.util.conf;

import com.xiaogangkui.util.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Created by luchunyu
 */
@Configuration
public class InterceptorConf implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //这里可以添加多个拦截器
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**");
    }
}
