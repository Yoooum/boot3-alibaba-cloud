package com.prprv.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.prprv.common.entity.Order;
import com.prprv.common.entity.Product;
import com.prprv.order.controller.client.ProductClient;
import com.prprv.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Yoooum
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderController {
    private final ProductClient productClient;
    private final OrderService orderService;
    private final RabbitTemplate rabbitTemplate;
    //准备买1件商品
    @GetMapping("/order/product/{product_id}")
    public Order order(@PathVariable Long product_id) {
        log.info(">>客户下单，这时候要调用商品微服务查询商品信息");

        Product prod = productClient.findById(product_id);

        if (prod == null) {
            return Order.builder().username("下单失败").build();
        }

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

        //发送消息
        rabbitTemplate.convertAndSend("myQueue", "A " + order.toString());

        return order;
    }

    @GetMapping("/order/message1")
    public String message1() {
        orderService.message();
        return "message1";
    }

    int i = 0;

    @GetMapping("/order/message2")
    @SentinelResource(
            value = "message2",
            blockHandlerClass = OrderBlockHandler.class,
            blockHandler = "blockHandler",
            fallbackClass = OrderFallback.class,
            fallback = "fallback"
    )
    public String message2() {
        orderService.message();
        i++;
        //异常比例为0.333
        if (i % 3 == 0) {
            throw new RuntimeException();
        }
        return "message2";
    }

    //BlockException时进入的方法
    public String blockHandler(BlockException ex) {
        log.error("{}", ex);
        return "接口被限流或者降级了...";
    }

    //Throwable时进入的方法
    public String fallback(Throwable throwable) {
        log.error("{}", throwable);
        return "接口发生异常了...";
    }

    @GetMapping("/order/message3")
    @SentinelResource("message3")
    public String message3(String name, Integer age) {
        return name + age;
    }


}

