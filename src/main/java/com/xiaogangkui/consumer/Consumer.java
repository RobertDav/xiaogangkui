package com.xiaogangkui.consumer;

import lombok.extern.log4j.Log4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author Created by luchunyu
 */
@Component
@Log4j
public class Consumer {

    @KafkaListener(groupId = "test", topics = {"testTopic"})
    public void listen(String content) {
        log.info("接收消息为:" + content);
    }
}
