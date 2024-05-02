package com.cristianbyte.hire_hub_jpa_dto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cristianbyte.hire_hub_jpa_dto.services.interfaces.IVacantService;
import com.cristianbyte.hire_hub_jpa_dto.utils.dto.request.VacantRequest;
import com.cristianbyte.hire_hub_jpa_dto.utils.dto.response.VacantResponse;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/vacants")
@AllArgsConstructor
public class VacantController {
    
    @Autowired
    private final IVacantService vacantService;

    @GetMapping
    public ResponseEntity<Page<VacantResponse>> getAll(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int size) {
        
        return ResponseEntity.ok(this.vacantService.GetAll(page - 1, size));
    }

    @PostMapping
    public ResponseEntity<VacantResponse> create(@Validated @RequestBody VacantRequest vacant) {
        return ResponseEntity.ok(this.vacantService.create(vacant));
    }
    
    
}
