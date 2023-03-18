package com.manideep.productservice.service;

import com.manideep.productservice.dto.ProductRequest;
import com.manideep.productservice.dto.ProductResponse;
import com.manideep.productservice.model.Product;
import com.manideep.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price((productRequest.getPrice()))
                .build();

        Product response = productRepository.save(product);
        return new ProductResponse(response.getId(),response.getName(),response.getDescription(),response.getPrice());
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();

    }
}
