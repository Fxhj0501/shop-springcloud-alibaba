package io.shop.order.fegin.fallback.factory;

import feign.hystrix.FallbackFactory;
import io.shop.bean.Product;
import io.shop.order.fegin.ProductService;
import io.shop.utils.resp.Result;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceFallBackFactory implements FallbackFactory<ProductService> {
    @Override
    public ProductService create(Throwable cause) {
        return new ProductService() {
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
                result.setCodeMsg("触发了容错逻辑");
                return result;
            }
        };
    }
}
