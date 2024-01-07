package io.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan(value = { "io.shop.order.mapper" })
@EnableDiscoveryClient
public class OrderStarter {
    public static void main(String[] args){
        SpringApplication.run(OrderStarter.class, args);
    }
}