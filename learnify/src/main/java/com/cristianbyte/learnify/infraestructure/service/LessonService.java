package com.cristianbyte.learnify.infraestructure.service;

import org.springframework.data.domain.Page;

import com.cristianbyte.learnify.api.dto.request.LessonRequest;
import com.cristianbyte.learnify.api.dto.response.LessonResponse;
import com.cristianbyte.learnify.infraestructure.abstract_service.ILessonService;

public class LessonService implements ILessonService{

    @Override
    public Page<LessonResponse> getAll(int page, int size) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LessonResponse getById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LessonResponse create(LessonRequest request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LessonResponse update(Integer id, LessonRequest request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
