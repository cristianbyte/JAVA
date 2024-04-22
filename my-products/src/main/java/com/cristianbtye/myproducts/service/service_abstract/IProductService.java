package com.cristianbtye.myproducts.service.service_abstract;

import java.util.List;

import com.cristianbtye.myproducts.entities.Product;


public interface IProductService {
    public Product save(Product product);
    public List<Product> getAll();
    public Product findById(Product product);
    
    public boolean delete(Long id);
    public Product update(Long id);
    public List<Product> search(String name);
}
