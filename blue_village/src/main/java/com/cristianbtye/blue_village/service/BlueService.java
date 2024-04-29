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
    public BluEvent save(BluEvent bluEvent) {
        return this.bluEventRepo.save(bluEvent);
    }

    @Override
    public BluEvent update(BluEvent bluEvent) {
        return this.bluEventRepo.save(bluEvent);
    }

    @Override
    public boolean delete(String id) {
        this.bluEventRepo.deleteById(id);
        return true;
    }

    @Override
    public BluEvent findById(String id) {
        return this.bluEventRepo.findById(id).orElseThrow();
    }
    
}
