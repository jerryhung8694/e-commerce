package com.jerryhung.springbootmall.dao;

import com.jerryhung.springbootmall.dto.ProductQueryParam;
import com.jerryhung.springbootmall.dto.ProductRequest;
import com.jerryhung.springbootmall.model.Product;

import java.util.List;

public interface ProductDao {

    Integer countProducts(ProductQueryParam productQueryParam);

    List<Product> getProducts(ProductQueryParam productQueryParam);

    Product getProductById(int id);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(int productId, ProductRequest productRequest);

    void updateStock(int productId, int stock);

    void deleteProductById(int productId);
}
