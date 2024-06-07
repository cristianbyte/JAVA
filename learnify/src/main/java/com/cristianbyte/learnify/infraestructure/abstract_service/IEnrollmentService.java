package com.cristianbyte.learnify.infraestructure.abstract_service;

import com.cristianbyte.learnify.api.dto.request.EnrollmentRequest;
import com.cristianbyte.learnify.api.dto.response.EnrollmentResponse;

public interface IEnrollmentService extends CrudAbstractService<EnrollmentRequest, EnrollmentResponse, Integer>{
    
}
