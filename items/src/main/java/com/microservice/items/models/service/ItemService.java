package com.microservice.items.models.service;

import com.microservice.items.models.Item;
import com.microservice.items.models.Product;

import java.util.List;


public interface ItemService {
    public List<Item> findAll();
    public Item findAllById(Long id, Integer cantidad);
    public Product save(Product product);
    public Product update (Product product, Long id);
    public void deleteProduct(Long id);
}
