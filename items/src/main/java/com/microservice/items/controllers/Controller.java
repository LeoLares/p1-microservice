package com.microservice.items.controllers;

import com.microservice.items.models.Item;
import com.microservice.items.models.Product;
import com.microservice.items.models.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class Controller {
    private final Logger logger = LoggerFactory.getLogger(Controller.class);

    @Autowired
    private CircuitBreaker cbFactory;

    @Autowired
    @Qualifier("itemServiceFeign")
    public ItemService itemService;

    @GetMapping("/listarItem")
    public List<Item> listarItem() {
        return itemService.findAll();
    }

    // aplicar circuitbraker con anotaciones

    @io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker(name = "items")
    @GetMapping("/verItem/{id}/cantidad/{cantidad}")
    public Item verItem(@PathVariable Long id, @PathVariable Integer cantidad) {
        return itemService.findAllById(id, cantidad);
    }

    // metodo alternativo para el circuit braker
    public Item metodoAlternativo(Long id, Integer cantidad, Throwable e) {
        logger.info(e.getMessage());
        Item item = new Item();
        Product product = new Product();
        item.setCantidad(cantidad);
        product.setId(id);
        product.setName("Calculadora");
        product.setPrice(2000.00);
        item.setProduct(product);
        return item;

    }

    // el metodo alternatico debe tener el siguiente recurso
    public CompletableFuture<Item> metodoalternativo2(Long id, Integer cantidad, Throwable e) {
        logger.info(e.getMessage());
        Item item = new Item();
        Product product = new Product();
        item.setCantidad(cantidad);
        product.setId(id);
        product.setName("Calculadora");
        product.setPrice(3000.00);
        item.setProduct(product);
        return CompletableFuture.supplyAsync(() -> item);

    }

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public Product crear(@RequestBody Product product) {
        return itemService.save(product);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product update(@RequestBody Product product, Long id) {
        return itemService.update(product, id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        itemService.deleteProduct(id);
    }
}
