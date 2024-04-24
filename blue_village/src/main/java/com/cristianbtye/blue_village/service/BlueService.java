package com.cristianbtye.blue_village.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cristianbtye.blue_village.entity.BluEvent;
import com.cristianbtye.blue_village.repository.BluEventRepo;
import com.cristianbtye.blue_village.service.IService.Iservice;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class BlueService implements Iservice{

    @Autowired
    private final BluEventRepo bluEventRepo;

    @Override
    public List<BluEvent> getAll() {
        return this.bluEventRepo.findAll();
    }

    @Override
    public List<BluEvent> search(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }

    @Override
    public BluEvent save(BluEvent bluEvent) {
        return this.bluEventRepo.save(bluEvent);
    }

    @Override
    public BluEvent update(BluEvent bluEvent) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public boolean delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public BluEvent findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }
    
}
