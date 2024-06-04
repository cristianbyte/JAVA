package com.cristianbyte.learnify.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.cristianbyte.learnify.api.dto.request.LessonRequest;
import com.cristianbyte.learnify.api.dto.response.LessonResponse;
import com.cristianbyte.learnify.domain.entities.Lesson;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LessonMapper {

    @Mapping(target = "course_id.id", source = "course_id")
    Lesson lessonRequestToLesson(LessonRequest lessonRequest);
    
    LessonResponse lessonToLessonResponse(Lesson lesson);
    
    @Mapping(target = "course_id.id", source = "course_id")
    void udpateFromLessonRequest(LessonRequest request, @MappingTarget Lesson lesson);
}
