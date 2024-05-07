package com.cristianbyte.beautyhub.infraestructure.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cristianbyte.beautyhub.api.dto.request.ServiceRequest;
import com.cristianbyte.beautyhub.api.dto.response.ServiceResponse;
import com.cristianbyte.beautyhub.domain.repositories.ServiceRepository;
import com.cristianbyte.beautyhub.infraestructure.abstract_service.IServiceService;
import com.cristianbyte.beautyhub.utils.enums.SortType;

import lombok.AllArgsConstructor;

@Service
@org.springframework.transaction.annotation.Transactional
@AllArgsConstructor
public class ServiceService implements IServiceService {

    @Autowired
    private final ServiceRepository serviceRepo;

    @Override
    public ServiceResponse create(ServiceRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public ServiceResponse get(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public ServiceResponse update(ServiceRequest request, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Page<ServiceResponse> getAll(int page, int size, SortType sort) {
        if(page < 0) page = 0;

        PageRequest pagination = null;

        switch (sort) {
            case NONE -> pagination = PageRequest.of(page, size);
                        
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FILED_BY_SORT).ascending());
             
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(FILED_BY_SORT).descending());

        }

        this.serviceRepo.findAll(pagination);

        return null;
    }

    @Override
    public List<ServiceResponse> search(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }
    
}
