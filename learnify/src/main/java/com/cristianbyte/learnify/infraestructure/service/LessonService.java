package com.cristianbyte.learnify.infraestructure.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cristianbyte.learnify.api.dto.request.LessonRequest;
import com.cristianbyte.learnify.api.dto.response.LessonResponse;
import com.cristianbyte.learnify.api.mapper.LessonMapper;
import com.cristianbyte.learnify.domain.entities.Lesson;
import com.cristianbyte.learnify.domain.repositories.LessonRepository;
import com.cristianbyte.learnify.infraestructure.abstract_service.ILessonService;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Service
public class LessonService implements ILessonService{

    @Autowired
    private final LessonRepository lessonRepository;

    @Autowired
    private final LessonMapper lessonMapper;

    @Override
    public Page<LessonResponse> getAll(int page, int size) {
        if (page <0) page = 0;
        PageRequest pagination = PageRequest.of(page -1, size);
        return this.lessonRepository.findAll(pagination).map(lessonMapper::lessonToLessonResponse);

    }

    @Override
    public LessonResponse getById(Integer id) {
        Lesson lesson = this.find(id);
        return this.lessonMapper.lessonToLessonResponse(lesson);
    }

    @Override
    public LessonResponse create(LessonRequest request) {
        Lesson newLesson = this.lessonMapper.lessonRequestToLesson(request);
        return this.lessonMapper.lessonToLessonResponse(this.lessonRepository.save(newLesson));
    }

    @Override
    public LessonResponse update(Integer id, LessonRequest request) {
        Lesson lessonToUpdate = this.find(id);
        lessonMapper.udpateFromLessonRequest(request, lessonToUpdate);
        lessonToUpdate.setId(id);
        return this.lessonMapper.lessonToLessonResponse(this.lessonRepository.save(lessonToUpdate));
    }

    @Override
    public void delete(Integer id) {
        this.lessonRepository.deleteById(id);
    }    

    private Lesson find(Integer id) {
        return this.lessonRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Lesson> saveAll(List<LessonRequest> requestList) {
        List<Lesson> lessonList;
        if (requestList == null || requestList.isEmpty()) {
            throw new IllegalArgumentException("The request list is empty");
        }else{
            lessonList = requestList.stream().map(lesson -> lessonMapper.lessonRequestToLesson(lesson)).toList();
        }
        return this.lessonRepository.saveAll(lessonList);
    }
}
