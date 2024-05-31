package com.cristianbyte.learnify.infraestructure.abstract_service;

import com.cristianbyte.learnify.api.dto.request.CourseRequest;
import com.cristianbyte.learnify.api.dto.response.CourseResponse;

public interface ICourseService extends CrudAbstractService<CourseRequest, CourseResponse, Integer>{
    
}
