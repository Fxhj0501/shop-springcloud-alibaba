package io.shop.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import io.shop.order.service.SentinelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class SentinelController {
    private int count = 0;
    @Autowired
    private SentinelService sentinelService;

    @GetMapping(value = "/request_sentinel1")
    public String requestSentinel1(){
        log.info("测试Sentinel1");
        sentinelService.sendMessage();
        return "sentinel1";
    }
    @GetMapping(value = "/request_sentinel2")
    public String requestSentinel2(){
        log.info("测试Sentinel2");
        sentinelService.sendMessage();
        return "sentinel2";
    }

    @GetMapping(value = "/request_sentinel3")
    @SentinelResource("request_sentinel3")
    public String requestSentinel3(){
        log.info("测试Sentinel3");
        sentinelService.sendMessage();
        return "sentinel3";
    }
    @GetMapping(value = "/request_sentinel4")
    @SentinelResource("request_sentinel4")
    public String requestSentinel4(){
        log.info("测试Sentinel4");
        count++;
        //模拟异常，比例为50%
        if (count % 2 == 0){
            throw new RuntimeException("演示基于异常比例熔断");
        }
        return "sentinel4";
    }
}
