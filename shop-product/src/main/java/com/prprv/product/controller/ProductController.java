package com.prprv.product.controller;

import com.prprv.common.entity.Product;
import com.prprv.product.service.ProductService;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yoooum
 */
@Slf4j
@RestController
public class ProductController {
    @Resource
    private ProductService productService;

    @GetMapping("/product/{id}")
    public Product findById(@PathVariable Long id) {
        log.info(">>商品微服务调用商品服务查询商品信息");

        return productService.findById(id);
    }
}
