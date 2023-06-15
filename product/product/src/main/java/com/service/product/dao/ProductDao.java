package com.service.product.dao;

import com.service.product.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductDao extends CrudRepository<Product , Long> {
}
