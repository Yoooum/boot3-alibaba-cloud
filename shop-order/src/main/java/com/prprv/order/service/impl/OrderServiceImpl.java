package com.prprv.order.service.impl;

import com.prprv.common.entity.Order;
import com.prprv.order.repo.OrderRepository;
import com.prprv.order.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


/**
 * @author Yoooum
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderRepository orderRepository;


    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }
}
