package com.xiaogangkui.controller;

import com.xiaogangkui.dto.ResponseMsg;
import com.xiaogangkui.impl.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

@RestController
public class BlockController {
    private static final Logger log = LoggerFactory.getLogger(BlockController.class);

    @Autowired
    private TaskService taskService;

    private  DeferredResult<String> deferredResult = new DeferredResult<>();

    @RequestMapping(value = "/getResult", method = RequestMethod.GET)
    public ResponseMsg<String> getResult() {

        log.info("接收请求，开始处理...");
        ResponseMsg<String> result = taskService.getResult();
        log.info("接收任务线程完成并退出");

        return result;
    }
    @GetMapping(value = "/getCallableResult")
    public ResponseMsg<String> getCallableResult() throws Exception {
        log.info("接收请求，开始处理...");

        Callable<ResponseMsg<String>> result =  (()->{return taskService.getResult();});

        log.info("接收任务线程完成并退出"+result);
        return result.call();
    }

    @RequestMapping("/testDeferredResult")
    public DeferredResult<String> testDeferredResult() {
        return deferredResult;
    }
    @RequestMapping("/setDeferredResult")
    public String setDeferredResult() {
        deferredResult.setResult("Test result!");
        return "succeed";
    }

}
