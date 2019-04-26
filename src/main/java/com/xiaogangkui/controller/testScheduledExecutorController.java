package com.xiaogangkui.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/testScheduled")
public class testScheduledExecutorController {


    @GetMapping(value = "/testLatterLoad")
    public  void testLatterLoad(){
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(new Thread(()->System.out.println("hello")),10, TimeUnit.SECONDS);
    }
}

