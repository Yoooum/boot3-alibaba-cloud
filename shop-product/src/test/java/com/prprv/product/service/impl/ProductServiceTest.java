package com.prprv.product.service.impl;

import com.prprv.common.entity.Product;
import com.prprv.product.service.ProductService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Yoooum
 */
@SpringBootTest
class ProductServiceTest {
    @Resource
    private ProductService productService;

    @Test
    void findById() {
        Product product = productService.findById(1L);
        System.out.println(product);
    }
}
