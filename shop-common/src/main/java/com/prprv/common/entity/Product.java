package com.prprv.common.entity;

import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * @author Yoooum
 */
@Data
@Entity(name = "shop_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//主键
    private String name;//商品名称
    private Double price;//商品价格
    private Integer stock;//库存
}
