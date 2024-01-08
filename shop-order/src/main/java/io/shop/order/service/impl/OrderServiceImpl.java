package io.shop.order.service.impl;

import com.alibaba.fastjson.JSONObject;
import io.shop.bean.Order;
import io.shop.bean.OrderItem;
import io.shop.bean.Product;
import io.shop.bean.User;
import io.shop.order.mapper.OrderItemMapper;
import io.shop.order.mapper.OrderMapper;
import io.shop.order.service.OrderService;
import io.shop.params.OrderParams;
import io.shop.utils.constants.HttpCode;
import io.shop.utils.resp.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    private String userServer = "server-user";
    private String productServer = "server-product";

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrder(OrderParams orderParams) {
        if (orderParams.isEmpty()){
            throw new RuntimeException("参数异常: " + JSONObject.toJSONString(orderParams));
        }
        // 1. 获取用户信息
        User user = restTemplate.getForObject("http://" + userServer + "/user/get/" + orderParams.getUserId(), User.class);
        if (user == null){
            throw new RuntimeException("未获取到用户信息: " + JSONObject.toJSONString(orderParams));
        }
        // 2. 获取商品信息
        Product product = restTemplate.getForObject("http://" + productServer + "/product/get/" + orderParams.getProductId(), Product.class);
        if (product == null){
            throw new RuntimeException("未获取到商品信息: " + JSONObject.toJSONString(orderParams));
        }

        Order order = new Order();
        order.setAddress(user.getAddress());
        order.setPhone(user.getPhone());
        order.setUserId(user.getId());
        order.setUsername(user.getUsername());
        order.setTotalPrice(product.getProPrice().multiply(BigDecimal.valueOf(orderParams.getCount())));
        orderMapper.insert(order);

        OrderItem orderItem = new OrderItem();
        orderItem.setNumber(orderParams.getCount());
        orderItem.setOrderId(order.getId());
        orderItem.setProId(product.getId());
        orderItem.setProName(product.getProName());
        orderItem.setProPrice(product.getProPrice());
        orderItemMapper.insert(orderItem);

        Result<Integer> result = restTemplate.getForObject("http://" + productServer + "/product/update_count/" + orderParams.getProductId() + "/" + orderParams.getCount(), Result.class);
        if (result.getCode() != HttpCode.SUCCESS){
            throw new RuntimeException("库存扣减失败");
        }
        log.info("库存扣减成功");
    }

//    private String getServiceUrl(String serviceName){
//        List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);
//        int idx = new Random().nextInt(instances.size());
//        ServiceInstance serviceInstance = instances.get(idx);
//        String url = serviceInstance.getHost() + ":" + serviceInstance.getPort();
//        log.info("负载均衡后的服务地址为:{}", url);
//        return url;
//    }
}
