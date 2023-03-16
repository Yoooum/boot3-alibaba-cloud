package com.prprv.order.controller;

import com.prprv.common.entity.Order;
import com.prprv.common.entity.Product;
import com.prprv.order.service.OrderService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


/**
 * @author Yoooum
 */
@Slf4j
@RestController
public class OrderController {
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private OrderService orderService;
    //准备买1件商品
    @GetMapping("/order/product/{product_id}")
    public Order order(@PathVariable Long product_id) {
        log.info(">>客户下单，这时候要调用商品微服务查询商品信息");

        //直接使用微服务名字， 从nacos中获取服务地址
        //通过restTemplate调用商品微服务
        Product prod = restTemplate.getForObject("http://shop-product/product/" + product_id, Product.class);
        log.info(">>商品信息,查询结果:" + prod);
        Order order = Order.builder()
                .id(1L)
                .username("测试用户")
                .productId(prod.getId())
                .name(prod.getName())
                .price(prod.getPrice())
                .number(1)
                .build();
        log.info(">>创建订单:" + order);
        orderService.save(order);
        return order;
    }
}
