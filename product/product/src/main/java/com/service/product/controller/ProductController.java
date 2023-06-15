package com.service.product.controller;

import com.service.product.entity.Product;
import com.service.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/listar")
    public List<Product> listar(){
        return productService.findAll();
    }

    @GetMapping("ver/{id}")
    public Product detalle(@PathVariable Long id) throws InterruptedException {

        if (id.equals(10L)){
            throw new IllegalStateException("Product no search");
        }
        if (id.equals(7L)){
            TimeUnit.SECONDS.sleep(5L);
        }
        Product product = productService.findById(id);
        return  product;
    }

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product){
        return productService.crear(product);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Product> update (@RequestBody Product product, Long id){
        Product productDB = productService.findById(id);
        if (productDB == null){
            return ResponseEntity.notFound().build();
        }

        productDB.setName(product.getName());
        productDB.setPrice(product.getPrice());
        productService.crear(productDB);
        return ResponseEntity.ok(productDB);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        productService.deleteProduct(id);
    }
}
