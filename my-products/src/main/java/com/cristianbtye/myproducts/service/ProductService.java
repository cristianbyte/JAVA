package com.cristianbtye.myproducts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cristianbtye.myproducts.entities.Product;
import com.cristianbtye.myproducts.repository.*;
import com.cristianbtye.myproducts.service.service_abstract.IProductService;

import lombok.AllArgsConstructor;

/**
 * ProductService
 */
@Service
@AllArgsConstructor
public class ProductService implements IProductService{

    @Autowired
    private final ProductRepo productRepo;

    @Override
    public Product save(Product product) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public List<Product> getAll() {
        return this.productRepo.findAll();
    }

    @Override
    public Product findById(Product product) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public boolean delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Product update(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public List<Product> search(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }
    
}