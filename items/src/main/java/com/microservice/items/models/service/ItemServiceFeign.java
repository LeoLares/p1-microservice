package com.microservice.items.models.service;

import com.microservice.items.client.ProductClientRest;
import com.microservice.items.models.Item;
import com.microservice.commons.models.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("itemServiceFeign")
public class ItemServiceFeign implements ItemService {

    @Autowired
    private ProductClientRest productClientRest;
    @Override
    public List<Item> findAll() {
        return productClientRest.listar().stream().map(product -> new Item(product, 10)).collect(Collectors.toList());
    }

    @Override
    public Item findAllById(Long id, Integer cantidad) {
        return new Item(productClientRest.detalle(id), cantidad);
    }

    @Override
    public Product save(Product product) {
        return null;
    }

    @Override
    public Product update(Product product, Long id) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
