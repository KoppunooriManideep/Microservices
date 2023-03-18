package com.manideep.productservice.service;

import com.manideep.productservice.dto.ProductRequest;
import com.manideep.productservice.dto.ProductResponse;
import com.manideep.productservice.model.Product;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);
    List<Product> getAllProducts();
}
