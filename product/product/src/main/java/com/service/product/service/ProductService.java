package com.service.product.service;


import com.microservice.commons.models.entity.Product;

import java.util.List;

public interface ProductService {

    public List<Product> findAll();
    public Product findById(Long id);

    public Product crear(Product product);
    public void deleteProduct(Long id);
}
