package com.prprv.product.service.impl;

import com.prprv.common.entity.Product;
import com.prprv.product.repo.ProductRepository;
import com.prprv.product.service.ProductService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author Yoooum
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductRepository productRepository;


    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
}
