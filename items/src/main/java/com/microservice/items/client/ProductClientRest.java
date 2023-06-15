package com.microservice.items.client;

import com.microservice.items.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "service-products", url = "localhost:8590/api/products")
public interface ProductClientRest {

    @GetMapping("/listar")
    public List<Product> listar();
    @GetMapping("/ver/{id}")
    public Product detalle(@PathVariable Long id);
}
