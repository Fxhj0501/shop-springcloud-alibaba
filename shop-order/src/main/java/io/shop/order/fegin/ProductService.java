package io.shop.order.fegin;

import io.shop.bean.Product;
import io.shop.order.fegin.fallback.ProductServiceFallBack;
import io.shop.order.fegin.fallback.factory.ProductServiceFallBackFactory;
import io.shop.utils.resp.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @description 调用商品微服务的接口
 */
@FeignClient(value = "server-product", fallbackFactory = ProductServiceFallBackFactory.class)
//@FeignClient(value = "server-product")
public interface ProductService {
    /**
     * 获取商品信息
     */
    @GetMapping(value = "/product/get/{pid}")
    Product getProduct(@PathVariable("pid") Long pid);

    /**
     * 更新库存数量
     */
    @GetMapping(value = "/product/update_count/{pid}/{count}")
    Result<Integer> updateCount(@PathVariable("pid") Long pid, @PathVariable("count") Integer count);
}
