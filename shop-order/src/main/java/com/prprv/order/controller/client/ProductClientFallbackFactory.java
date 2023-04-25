package com.prprv.order.controller.client;

import com.prprv.common.entity.Product;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author Yoooum
 */
@Component
public class ProductClientFallbackFactory implements FallbackFactory<ProductClient> {
    @Override
    public ProductClient create(Throwable cause) {
        return new ProductClient() {
            @Override
            public Product findById(Long id) {
                Product product = new Product();
                product.setId(-1L);
                product.setName("产品信息查询出错");
                return product;
            }
        };
    }
}
