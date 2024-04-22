package com.cristianbtye.myproducts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cristianbtye.myproducts.entities.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long >{

}