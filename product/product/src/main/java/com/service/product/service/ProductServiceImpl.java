package com.service.product.service;

import com.service.product.dao.ProductDao;
import com.service.product.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl  implements ProductService{

    @Autowired
    private ProductDao productDao;
    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return (List<Product>) productDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Product findById(Long id) {
        return productDao.findById(id).orElse(null);
    }

    @Transactional
    public Product crear(Product product){
        product.setCreateAat(new Date());
        return productDao.save(product);
    }

    @Transactional
    public void deleteProduct(Long id){
        productDao.deleteById(id);
    }
}
