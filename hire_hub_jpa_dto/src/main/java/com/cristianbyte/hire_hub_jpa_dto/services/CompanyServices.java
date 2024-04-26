package com.cristianbyte.hire_hub_jpa_dto.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cristianbyte.hire_hub_jpa_dto.entities.Company;
import com.cristianbyte.hire_hub_jpa_dto.repositories.CompanyRepo;
import com.cristianbyte.hire_hub_jpa_dto.services.interfaces.ICompanyService;
import com.cristianbyte.hire_hub_jpa_dto.utils.dto.request.CompanyRequest;
import com.cristianbyte.hire_hub_jpa_dto.utils.dto.response.CompanyResponse;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CompanyServices implements ICompanyService{

    @Autowired
    private final CompanyRepo companyRepo;

    @Override
    public Page<CompanyResponse> GetAll(int page, int size) {
        // Pagination
        if(page < 0) page = 0;
        PageRequest pagination = PageRequest.of(page, size);
        // Calling the repo
        //company -> this.entityToResponse(company) same as this::entityToResponse
        return this.companyRepo.findAll(pagination).map(this::entityToResponse);
    }

    @Override
    public CompanyResponse create(CompanyRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public CompanyResponse update(CompanyRequest request, String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public CompanyResponse getById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }



    private CompanyResponse entityToResponse(Company entity){
        CompanyResponse response = new CompanyResponse();
        // response.setId(entity.getId());
        // response.setName(entity.getName());
        // response.setLocation(entity.getLocation());
        // response.setContact(entity.getContact());
        // Bean ultils save you to do all of that
        BeanUtils.copyProperties(entity, response); 
        
        return response;
    }
    
}
