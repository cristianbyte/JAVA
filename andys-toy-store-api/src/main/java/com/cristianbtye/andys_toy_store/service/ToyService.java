package com.cristianbtye.andys_toy_store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cristianbtye.andys_toy_store.entities.Toy;
import com.cristianbtye.andys_toy_store.repository.*;
import com.cristianbtye.andys_toy_store.service.service_abstract.IToyService;

import lombok.AllArgsConstructor;

/**
 * ToyService
 */
@Service
@AllArgsConstructor
public class ToyService implements IToyService{

    @Autowired
    private final ToyRepository toyRepo;

    @Override
    public Toy save(Toy toy) {
        return this.toyRepo.save(toy);
    }

    @Override
    public List<Toy> getAll() {
        return this.toyRepo.findAll();
    }

    @Override
    public Toy findById(Long id) {
        return this.toyRepo.findById(id).orElse(null);
    }

    @Override
    public boolean delete(Long id) {
        this.toyRepo.deleteById(id);
        return true;
    }

    @Override
    public List<Toy> search(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }

    @Override
    public Toy update(Toy toy) {
        return this.toyRepo.save(toy);
    }
    
}