package com.xiaogangkui.util.conf;


import com.xiaogangkui.util.common.BeanMapperUtil;
import com.xiaogangkui.util.common.BeanUtil;
import com.xiaogangkui.util.common.ExcelUtil;
import com.xiaogangkui.util.common.HttpUtil;
import okhttp3.OkHttpClient;
import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Created by luchunyu
 */
@Configuration
public class CommonConfig {

    @Bean
    public HttpUtil httpUtil() {
        return new HttpUtil(new OkHttpClient());
    }

    @Bean
    public ExcelUtil excelUtil() {
        return new ExcelUtil();
    }

    @Bean
    public BeanMapperUtil beanMapperUtil() {
        BeanMapperUtil beanMapperUtil = new BeanMapperUtil();
        beanMapperUtil.setMapper(new DozerBeanMapper());
        return beanMapperUtil;
    }

    @Bean
    public BeanUtil getBeanUtil() {
        return new BeanUtil();
    }


}
