package com.prprv.order.repo;

import com.prprv.common.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Yoooum
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}
