package com.prprv.order.controller.client;

import com.prprv.common.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Yoooum
 */
@FeignClient(
        name = "shop-product",
        fallback = ProductClientFullback.class)
public interface ProductClient {
    @GetMapping("/product/{id}")
    Product findById(@PathVariable Long id);
}
