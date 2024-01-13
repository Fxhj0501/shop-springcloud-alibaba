package io.shop.order.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import io.shop.order.service.SentinelService;
import org.springframework.stereotype.Service;

@Service
public class SentinelServiceImpl implements SentinelService {
    @Override
    @SentinelResource("sendMessage")
    public void sendMessage() {
        System.out.println("测试Sentinel的链路流控模式");
    }
}