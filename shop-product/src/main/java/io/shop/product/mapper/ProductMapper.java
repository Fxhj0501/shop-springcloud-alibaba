package io.shop.product.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.shop.bean.Product;
import org.apache.ibatis.annotations.Param;

public interface ProductMapper extends BaseMapper<Product> {

    /**
     * 扣减商品库存
     */
    int updateProductStockById(@Param("count") Integer count, @Param("id") Long id);
}