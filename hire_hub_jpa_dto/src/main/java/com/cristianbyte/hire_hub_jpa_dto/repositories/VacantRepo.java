package com.cristianbyte.hire_hub_jpa_dto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cristianbyte.hire_hub_jpa_dto.entities.Vacant;

public interface VacantRepo extends JpaRepository <Vacant, Long>{
    
}
