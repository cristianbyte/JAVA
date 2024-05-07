package com.cristianbyte.beautyhub.domain.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cristianbyte.beautyhub.domain.entity.Services;

@Repository
public interface ServiceRepository extends JpaRepository<Services,Long>{
    @Query("select s from services s where s.price between :min and :max")
    public List<Services> selectBetweenPrice(BigDecimal min, BigDecimal max);

    // @Query(value = "SELECT s FROM services s WHERE s.name LIKE :search", nativeQuery = true)
    @Query(value = "SELECT s FROM services s WHERE s.name LIKE :search")
    public List<Services> findByNameContaining(String search);

} 
