package com.cristianbtye.blue_village.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cristianbtye.blue_village.entity.BluEvent;

@Repository
public interface BluEventRepo extends JpaRepository<BluEvent, String> {}
