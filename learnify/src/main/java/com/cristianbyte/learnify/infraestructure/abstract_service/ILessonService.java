package com.cristianbyte.learnify.infraestructure.abstract_service;

import java.util.List;

import com.cristianbyte.learnify.api.dto.request.LessonRequest;
import com.cristianbyte.learnify.api.dto.response.LessonResponse;

public interface ILessonService extends CrudAbstractService<LessonRequest, LessonResponse, Integer> {
    void saveAll(List<LessonRequest> requestList);
}
