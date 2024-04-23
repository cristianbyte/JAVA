package com.cristianbtye.andys_toy_store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cristianbtye.andys_toy_store.entities.Toy;

// Toy <- Entidad | Long <- IDtype
@Repository
public interface ToyRepository extends JpaRepository<Toy, Long >{

}