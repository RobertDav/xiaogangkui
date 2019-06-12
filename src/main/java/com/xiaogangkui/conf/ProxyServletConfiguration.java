package com.xiaogangkui.conf;

import org.mitre.dsmiley.httpproxy.ProxyServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ProxyServletConfiguration implements EnvironmentAware {

    @Value("${servlet.proxy.target_url}")
    private String target_url;
    @Value("${servlet.proxy.servlet_url}")
    private String servlet_url;

    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new ProxyServlet(), servlet_url);
        //这个setName必须要设置，并且多个的时候，名字需要不一样
        servletRegistrationBean.setName("suitone");
        servletRegistrationBean.addInitParameter("targetUri",target_url);
        servletRegistrationBean.addInitParameter(ProxyServlet.P_LOG, "true");
        return servletRegistrationBean;
    }



    @Override
    public void setEnvironment(Environment environment) {

    }

}
