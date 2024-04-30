package com.cristianbyte.hire_hub_jpa_dto.services;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cristianbyte.hire_hub_jpa_dto.entities.Company;
import com.cristianbyte.hire_hub_jpa_dto.entities.Vacant;
import com.cristianbyte.hire_hub_jpa_dto.repositories.CompanyRepo;
import com.cristianbyte.hire_hub_jpa_dto.services.interfaces.ICompanyService;
import com.cristianbyte.hire_hub_jpa_dto.utils.dto.request.CompanyRequest;
import com.cristianbyte.hire_hub_jpa_dto.utils.dto.response.CompanyResponse;
import com.cristianbyte.hire_hub_jpa_dto.utils.dto.response.VacantToCompanyResponse;
import com.cristianbyte.hire_hub_jpa_dto.utils.exeptions.IdNotFound;

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
        Company company = this.requestToEntity(request, new Company());
        return this.entityToResponse(this.companyRepo.save(company));
    }

    @Override
    public CompanyResponse update(CompanyRequest request, String id) {
        Company toUpdate = this.find(id);
        Company updated = this.requestToEntity(request, toUpdate);
        return this.entityToResponse(this.companyRepo.save(updated));
    }

    @Override
    public void delete(String id) {
        Company company = this.find(id);
        this.companyRepo.delete(company);
    }

    @Override
    public CompanyResponse getById(String id) {
        Company company = this.find(id);

        return this.entityToResponse(company);
    }

    private Company find(String id){
        return this.companyRepo.findById(id).orElseThrow(() -> new IdNotFound("Company"));
    }


    private CompanyResponse entityToResponse(Company entity){
        CompanyResponse response = new CompanyResponse();
        // response.setId(entity.getId());
        // response.setName(entity.getName());
        // response.setLocation(entity.getLocation());
        // response.setContact(entity.getContact());
        // Bean ultils save you to do all of that
        BeanUtils.copyProperties(entity, response); 
        response.setVacants(entity.getVacants().stream().map(this::VacantToCompanyResponse).collect(Collectors.toList()));
        return response;
    }

    private VacantToCompanyResponse VacantToCompanyResponse(Vacant entity){
        VacantToCompanyResponse response = new VacantToCompanyResponse();
        BeanUtils.copyProperties(entity, response);

        return response;
    }

    private Company requestToEntity(CompanyRequest entity, Company company){
        
        company.setContact(entity.getContact());
        company.setLocation(entity.getLocation());
        company.setName(entity.getName());
        company.setVacants(new ArrayList<>());

        return company;
    }
    
}
