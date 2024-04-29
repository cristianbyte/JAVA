package com.cristianbyte.hire_hub_jpa_dto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cristianbyte.hire_hub_jpa_dto.services.interfaces.ICompanyService;
import com.cristianbyte.hire_hub_jpa_dto.utils.dto.request.CompanyRequest;
import com.cristianbyte.hire_hub_jpa_dto.utils.dto.response.CompanyResponse;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(path="/company")
@AllArgsConstructor
public class CompanyController {
    
    @Autowired
    private final ICompanyService companyService;


    // pathvariable '/' requestparam '?'
    @GetMapping
    public ResponseEntity<Page<CompanyResponse>> GetAll(@RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "3") int size){
        return ResponseEntity.ok(this.companyService.GetAll(page-1, size));
    };

    @PostMapping
    public ResponseEntity<CompanyResponse> insert(@RequestBody CompanyRequest company) {       
        return ResponseEntity.ok(this.companyService.create(company));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CompanyResponse> get(@PathVariable String id){
        return ResponseEntity.ok(this.companyService.getById(id));
    }
}