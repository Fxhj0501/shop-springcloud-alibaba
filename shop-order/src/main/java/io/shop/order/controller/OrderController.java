package io.shop.order.controller;

import com.alibaba.fastjson.JSONObject;
import io.shop.order.service.OrderService;
//import io.shop.params.OrderParams;
import io.shop.params.OrderParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/submit_order")
    public String submitOrder(OrderParams orderParams){
        log.info("提交订单时传递的参数:{}", JSONObject.toJSONString(orderParams));
        orderService.saveOrder(orderParams);
        return "success";
    }
    @GetMapping(value = "/test_sentinel")
    public String testSentinel(){
        log.info("测试Sentinel");
        return "sentinel";
    }
    @GetMapping(value = "/test_sentinel2")
    public String testSentinel2(){
        log.info("测试Sentinel2");
        return "sentinel2";
    }
}
