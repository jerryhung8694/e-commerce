package com.jerryhung.springbootmall.service;

import com.jerryhung.springbootmall.dto.ProductQueryParam;
import com.jerryhung.springbootmall.dto.ProductRequest;
import com.jerryhung.springbootmall.model.Product;

import java.util.List;

public interface ProductService {

    Integer countProducts(ProductQueryParam productQueryParam);

    List<Product> getProducts(ProductQueryParam productQueryParam);

    Product getProductById(int id);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(int productId, ProductRequest productRequest);

    void deleteProductById(int productId);
}
