package io.shop.order.service;

import io.shop.params.OrderParams;

public interface OrderService {
    /**
     * 保存订单
     */
    void saveOrder(OrderParams orderParams);
}