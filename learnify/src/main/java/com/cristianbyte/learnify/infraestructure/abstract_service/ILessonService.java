package com.cristianbyte.learnify.infraestructure.abstract_service;

import java.util.List;

import com.cristianbyte.learnify.api.dto.request.LessonRequest;
import com.cristianbyte.learnify.api.dto.response.LessonResponse;
import com.cristianbyte.learnify.domain.entities.Lesson;

public interface ILessonService extends CrudAbstractService<LessonRequest, LessonResponse, Integer> {
    List<Lesson> saveAll(List<LessonRequest> requestList);
}
