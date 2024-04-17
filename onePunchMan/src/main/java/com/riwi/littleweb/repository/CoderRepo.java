package com.riwi.littleweb.repository;

import org.springframework.stereotype.Repository;

import com.riwi.littleweb.entity.Coder;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CoderRepo extends JpaRepository<Coder, Long>{
    
}
