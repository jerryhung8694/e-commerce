package com.jerryhung.springbootmall.dao;

import com.jerryhung.springbootmall.model.Product;

public interface ProductDao {

    Product getProductById(int id);
}
