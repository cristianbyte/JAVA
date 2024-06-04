package com.cristianbyte.learnify.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.cristianbyte.learnify.api.dto.request.CourseRequest;
import com.cristianbyte.learnify.api.dto.response.CourseResponse;
import com.cristianbyte.learnify.domain.entities.Course;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {UserMapper.class})
public interface CourseMapper {
    
    @Mapping(target = "lessons", ignore = true)
    Course courseRequestToCourse(CourseRequest courseRequest);
    
    @Mapping(target = "teacherName", source = "instructor_id.fullname")
    CourseResponse courseToCourseResponse(Course course);
    
    @Mapping(target = "lessons", ignore = true)
    void udpateFromCourseRequest(CourseRequest request, @MappingTarget Course user);
}