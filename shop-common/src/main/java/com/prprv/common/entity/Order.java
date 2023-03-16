package com.prprv.common.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Yoooum
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "shop_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//订单id
    private Long userId;//用户id
    private Long productId;//商品id
    private String username;//用户名
    private String name;//商品名称
    private Double price;//商品单价
    private Integer number;//购买数量
}
