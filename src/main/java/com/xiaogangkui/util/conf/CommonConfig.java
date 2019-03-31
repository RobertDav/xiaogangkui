package com.xiaogangkui.util.conf;


import com.suanhu.image.service.dto.AuthTokenService;
import com.suanhu.image.service.impl.ImageBaseService;
import com.suanhu.image.service.impl.QiniuService;
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

    @Bean
    public QiniuService getQiniuService(){
        return new QiniuService();
    }

    @Bean
    public ImageBaseService getImageBaseService(){
        ImageBaseService imageBaseService = new ImageBaseService();
        imageBaseService.setAccessKey("GeBgwnV9CB6QfEtc9eV98Ern986TQHwQSf-cJQE6");
        imageBaseService.setSecretKey("ebxR56ltoLlptHDIW4JwNKcaRSiaDijuzDvQtSov");
        imageBaseService.setPublicDomain("o9w8tdosw.bkt.clouddn.com");
        imageBaseService.setPrivateDomain("o9x64wd7c.bkt.clouddn.com");
        return imageBaseService;
    }
    @Bean
    public AuthTokenService authTokenService(){
        return new AuthTokenService();
    }
}
