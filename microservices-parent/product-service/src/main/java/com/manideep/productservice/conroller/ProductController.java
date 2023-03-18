package com.manideep.productservice.conroller;

import com.manideep.productservice.dto.ProductRequest;
import com.manideep.productservice.dto.ProductResponse;
import com.manideep.productservice.model.Product;
import com.manideep.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/product/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("createProduct")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest){
        return new ResponseEntity<>(productService.createProduct(productRequest), HttpStatus.CREATED);
    }

    @GetMapping("getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(),HttpStatus.OK);
    }
}
