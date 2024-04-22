package com.cristianbtye.andys_toy_store.service.service_abstract;

import java.util.List;

import com.cristianbtye.andys_toy_store.entities.Toy;


public interface IToyService {
    public List<Toy> getAll();
    public List<Toy> search(String name);

    public Toy save(Toy toy);
    public Toy update(Toy toy);
    public boolean delete(Long id);
    
    public Toy findById(Long id);
    
}
