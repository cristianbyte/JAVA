package com.cristianbyte.hire_hub_jpa_dto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cristianbyte.hire_hub_jpa_dto.entities.Company;

public interface CompanyRepo extends JpaRepository<Company, String>{
   
}
