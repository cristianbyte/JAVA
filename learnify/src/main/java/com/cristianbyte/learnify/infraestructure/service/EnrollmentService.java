package com.cristianbyte.learnify.infraestructure.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cristianbyte.learnify.api.dto.request.EnrollmentRequest;
import com.cristianbyte.learnify.api.dto.response.EnrollmentResponse;
import com.cristianbyte.learnify.api.mapper.EnrollmentMapper;
import com.cristianbyte.learnify.domain.entities.Enrollment;
import com.cristianbyte.learnify.domain.repositories.EnrollmentsRepository;
import com.cristianbyte.learnify.infraestructure.abstract_service.IEnrollmentService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class EnrollmentService implements IEnrollmentService{

    @Autowired
    private final EnrollmentsRepository enrollmentRepository;

    @Autowired
    private final EnrollmentMapper enrollmentMapper;

    @Override
    public Page<EnrollmentResponse> getAll(int page, int size) {
        if (page <0) page = 0;
        
        PageRequest pagination = PageRequest.of(page -1, size);

        return this.enrollmentRepository.findAll(pagination)
                .map(enrollmentMapper::enrollmentToEnrollmentResponse);
    }

    @Override
    public EnrollmentResponse getById(Integer id) {
        Enrollment response = this.find(id);
        return this.enrollmentMapper.enrollmentToEnrollmentResponse(response);
    }

    @Override
    public EnrollmentResponse create(EnrollmentRequest request) {
        Enrollment newEnrollment = this.enrollmentMapper.enrollmentRequestToEnrollment(request);
        newEnrollment.setDate(LocalDate.now());
        //Enrollment reference = this.enrollmentRepository.save(newEnrollment);
        //return this.enrollmentMapper.enrolmentToEnrollmentResponse(this.find(reference.getId()));
        return this.enrollmentMapper.enrollmentToEnrollmentResponse(this.enrollmentRepository.save(newEnrollment));
    }

    @Override
    public EnrollmentResponse update(Integer id, EnrollmentRequest request) {
        Enrollment enrollmentToUpdate = this.find(id);
        enrollmentMapper.udpateFromEnrollmentRequest(request, enrollmentToUpdate);
        enrollmentToUpdate.setId(id);
        return this.enrollmentMapper.enrollmentToEnrollmentResponse(this.enrollmentRepository.save(enrollmentToUpdate));
    }

    @Override
    public void delete(Integer id) {
        this.enrollmentRepository.deleteById(id);
    }
    
    private Enrollment find(Integer id) {
        return this.enrollmentRepository.findById(id).orElseThrow();
    }
}
