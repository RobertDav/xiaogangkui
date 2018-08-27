package com.xiaogangkui.timetask;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Created by luchunyu
 */
@Component
public class SyncEmployeeService {

    @Scheduled(cron = "0 0/1 * * * ?")
    public void testTaskTime() {
        System.out.println("当前时间" + System.currentTimeMillis());
    }
}
