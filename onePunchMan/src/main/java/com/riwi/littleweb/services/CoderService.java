package com.riwi.littleweb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.riwi.littleweb.entity.Coder;
import com.riwi.littleweb.repository.CoderRepo;

@Service
public class CoderService {

    @Autowired
    private CoderRepo objCoderRepo;

    public List<Coder> findAll(){
        return this.objCoderRepo.findAll();
    }
}
