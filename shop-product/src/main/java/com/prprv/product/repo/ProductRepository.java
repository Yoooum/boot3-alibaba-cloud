package com.prprv.product.repo;

import com.prprv.common.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Yoooum
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}
