package com.riwi.littleweb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
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

    /* Service to save a coder*/
    public Coder insert(Coder objCoder) {
        return this.objCoderRepo.save(objCoder);
    }

    public Coder update(Long id, Coder objCoder){

        Coder objCoderDB = this.findById(id);

        if(objCoder == null) return null;

        //Validate exist
        objCoderDB = objCoder;
        //save
        return this.objCoderRepo.save(objCoderDB);
    }

    public Coder findById(Long id){
        return this.objCoderRepo.findById(id).orElse(null);
    }

    public void delete(Long id){
        this.objCoderRepo.deleteById(id);;
    }

    public <Page>Coder fingPaginated(int page, int size){
        if(page < 0){
            page = 1;
        }

        Pageable objPageable = PageRequest.of(page, size);

        return this.objCoderRepo.findAll(objPageable);
    }

}
