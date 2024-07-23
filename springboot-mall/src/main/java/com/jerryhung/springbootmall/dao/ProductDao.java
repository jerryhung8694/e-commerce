package com.jerryhung.springbootmall.dao;

import com.jerryhung.springbootmall.dto.ProductRequest;
import com.jerryhung.springbootmall.model.Product;

public interface ProductDao {

    Product getProductById(int id);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(int productId, ProductRequest productRequest);

    void deleteProductById(int productId);
}
