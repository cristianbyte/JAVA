package com.cristianbyte.beautyhub.domain.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cristianbyte.beautyhub.domain.entity.Appointment;



@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    public Optional<Appointment> findByClientId(Long idClient);
}