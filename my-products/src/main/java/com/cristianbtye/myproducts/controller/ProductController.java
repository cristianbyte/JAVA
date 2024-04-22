package com.cristianbtye.myproducts.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cristianbtye.myproducts.entities.Product;
import com.cristianbtye.myproducts.service.service_abstract.IProductService;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("api/v1/products")
@AllArgsConstructor
public class ProductController {

    private final IProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>>  getAll() {
        return ResponseEntity.ok(this.productService.getAll());
    }
    
    
}
