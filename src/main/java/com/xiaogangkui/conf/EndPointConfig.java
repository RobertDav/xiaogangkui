package com.xiaogangkui.conf;

import com.xiaogangkui.endPoint.MyEndPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EndPointConfig {

    @Bean
    public MyEndPoint myEndPoint(){
        return new MyEndPoint();
    }
}
