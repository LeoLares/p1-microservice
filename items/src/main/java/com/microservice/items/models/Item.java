package com.microservice.items.models;

import com.microservice.items.models.Product;

public class Item {



    private Product product;
    private Integer cantidad;

    public Item(Product product, Integer cantidad){
        this.product = product;
        this.cantidad = cantidad;
    }

    public Item(){

    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Double getTotal(){
        return product.getPrice() * cantidad.doubleValue();
    }
}
