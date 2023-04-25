package com.prprv.order.controller;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Yoooum
 */
@Slf4j
public class OrderBlockHandler {
    //注意这里必须使用static修饰方法
    public static String blockHandler(BlockException ex) {
        log.error("{}", ex);
        return "接口被限流或者降级了...";
    }
}
