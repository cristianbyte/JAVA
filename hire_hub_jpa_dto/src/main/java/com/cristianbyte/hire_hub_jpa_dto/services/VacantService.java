package com.cristianbyte.hire_hub_jpa_dto.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cristianbyte.hire_hub_jpa_dto.entities.Company;
import com.cristianbyte.hire_hub_jpa_dto.entities.Vacant;
import com.cristianbyte.hire_hub_jpa_dto.repositories.CompanyRepo;
import com.cristianbyte.hire_hub_jpa_dto.repositories.VacantRepo;
import com.cristianbyte.hire_hub_jpa_dto.services.interfaces.IVacantService;
import com.cristianbyte.hire_hub_jpa_dto.utils.dto.request.VacantRequest;
import com.cristianbyte.hire_hub_jpa_dto.utils.dto.response.CompanyToVacantResponse;
import com.cristianbyte.hire_hub_jpa_dto.utils.dto.response.VacantResponse;
import com.cristianbyte.hire_hub_jpa_dto.utils.enums.StatusVacant;
import com.cristianbyte.hire_hub_jpa_dto.utils.exeptions.IdNotFound;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VacantService implements IVacantService{

    @Autowired
    private final VacantRepo vacantRepo;
    @Autowired
    private final CompanyRepo companyRepo;


    @Override
    public Page<VacantResponse> GetAll(int page, int size) {
        if(page > 0) page = 0;

        PageRequest pagination = PageRequest.of(page, size);

        return this.vacantRepo.findAll(pagination).map(this::entityToResponse);
    }

    @Override
    public VacantResponse create(VacantRequest request) {
        Company company = this.companyRepo.findById( request.getCompanyId()).orElseThrow(()->new IdNotFound("Company"));
        Vacant vacant = this.requesToVacant(request, new Vacant());
        vacant.setCompany(company);

        return this.entityToResponse(this.vacantRepo.save(vacant));
    }

    @Override
    public VacantResponse update(VacantRequest request, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
        this.vacantRepo.deleteById(id);
    }

    @Override
    public VacantResponse getById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    private VacantResponse entityToResponse(Vacant entity){
        VacantResponse response = new VacantResponse();

        BeanUtils.copyProperties(entity, response);

        CompanyToVacantResponse companyDto = new CompanyToVacantResponse();

        BeanUtils.copyProperties(entity.getCompany(), companyDto);

        response.setCompany(companyDto);

        return response;
    }

    private Vacant requesToVacant(VacantRequest request,Vacant entity){
        entity.setTitle(request.getTitle());
        entity.setDescription(request.getDescription());
        entity.setStatus(StatusVacant.ACTIVE);

        return entity;
    }
}
