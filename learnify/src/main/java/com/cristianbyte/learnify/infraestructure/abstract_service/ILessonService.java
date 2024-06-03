package com.cristianbyte.learnify.infraestructure.abstract_service;

import com.cristianbyte.learnify.api.dto.request.LessonRequest;
import com.cristianbyte.learnify.api.dto.response.LessonResponse;

public interface ILessonService extends CrudAbstractService<LessonRequest, LessonResponse, Integer> {
    
}
