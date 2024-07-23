package com.jerryhung.springbootmall.service;

import com.jerryhung.springbootmall.dto.ProductRequest;
import com.jerryhung.springbootmall.model.Product;

public interface ProductService {

    Product getProductById(int id);

    Integer createProduct(ProductRequest productRequest);
}
