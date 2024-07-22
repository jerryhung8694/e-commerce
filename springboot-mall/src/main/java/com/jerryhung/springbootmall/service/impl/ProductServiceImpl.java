package com.jerryhung.springbootmall.service.impl;

import com.jerryhung.springbootmall.dao.ProductDao;
import com.jerryhung.springbootmall.model.Product;
import com.jerryhung.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProductById(int id) {
        return productDao.getProductById(id);
    }
}
