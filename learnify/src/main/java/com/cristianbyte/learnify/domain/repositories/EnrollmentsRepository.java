package com.cristianbyte.learnify.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cristianbyte.learnify.domain.entities.Enrollment;

public interface EnrollmentsRepository extends JpaRepository<Enrollment, Integer>{
    
}
