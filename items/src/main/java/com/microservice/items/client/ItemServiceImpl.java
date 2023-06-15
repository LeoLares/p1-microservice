package com.microservice.items.client;

import com.microservice.items.models.Item;
import com.microservice.items.models.Product;
import com.microservice.items.models.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private RestTemplate restTemplate;
    @Override
    public List<Item> findAll() {
        List<Product> product = Arrays.asList(restTemplate.getForObject("http://localhost:8085/listar",Product[].class));
        return product.stream().map(product1 -> new Item(product1, 1)).collect(Collectors.toList());
    }

    @Override
    public Item findAllById(Long id, Integer cantidad) {
        Map<String,String> pathVarible = new HashMap<String,String>();
        pathVarible.put("id",id.toString());
        Product product = restTemplate.getForObject("http://localhost:8085/ve/{id}", Product.class,pathVarible);
        return new Item(product, cantidad);
    }

    @Override
    public Product save(Product product) {
        org.springframework.http.HttpEntity<Product> body = new org.springframework.http.HttpEntity<Product>(product);
        ResponseEntity<Product> response = restTemplate.exchange("http://service-products/crear",HttpMethod.PUT,body,Product.class);
        Product productResponse = response.getBody();
        return productResponse;
    }

    @Override
    public Product update(Product product, Long id) {
        Map<String,String> pathVarible = new HashMap<String,String>();
        pathVarible.put("id",id.toString());
        org.springframework.http.HttpEntity<Product> body = new org.springframework.http.HttpEntity<Product>(product);
        ResponseEntity<Product> response = restTemplate.exchange("http://service-products/update/{id}",HttpMethod.PUT,body,Product.class);
        Product productResponse = response.getBody();
        return productResponse;
    }

    @Override
    public void deleteProduct(Long id) {
    Map<String,String> pathVarible = new HashMap<String,String>();
    pathVarible.put("id",id.toString());
    restTemplate.delete("http://service-products/delete/{id}",pathVarible);
    }
}
