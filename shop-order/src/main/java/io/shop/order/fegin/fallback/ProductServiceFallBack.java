package io.shop.order.fegin.fallback;

import io.shop.bean.Product;
import io.shop.order.fegin.ProductService;
import io.shop.utils.resp.Result;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceFallBack implements ProductService {
    @Override
    public Product getProduct(Long pid) {
        Product product = new Product();
        product.setId(-1L);
        return product;
    }
    @Override
    public Result<Integer> updateCount(Long pid, Integer count) {
        Result<Integer> result = new Result<>();
        result.setCode(1001);
        result.setCodeMsg("调用商品微服务失败");
        return result;
    }
}
