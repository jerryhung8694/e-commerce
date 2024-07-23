package com.jerryhung.springbootmall.service;

import com.jerryhung.springbootmall.constant.ProductCategory;
import com.jerryhung.springbootmall.dto.ProductRequest;
import com.jerryhung.springbootmall.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts(ProductCategory category, String search);

    Product getProductById(int id);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(int productId, ProductRequest productRequest);

    void deleteProductById(int productId);
}
