package io.shop.product.service;

import io.shop.bean.Product;

public interface ProductService {

    /**
     * 根据商品id获取商品信息
     */
    Product getProductById(Long pid);


    /**
     * 扣减商品库存
     */
    int updateProductStockById(Integer count, Long id);
}