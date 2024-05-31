package com.cristianbyte.learnify.infraestructure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cristianbyte.learnify.api.dto.request.CourseRequest;
import com.cristianbyte.learnify.api.dto.response.CourseResponse;
import com.cristianbyte.learnify.api.mapper.CourseMapper;
import com.cristianbyte.learnify.domain.entities.Course;
import com.cristianbyte.learnify.domain.repositories.CourseRepository;
import com.cristianbyte.learnify.infraestructure.abstract_service.ICourseService;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class CourseService implements ICourseService{

    @Autowired
    private final CourseRepository courseRepository;

    @Autowired
    private final UserService userService;

    @Autowired
    private final CourseMapper courseMapper;

    @Override
    public Page<CourseResponse> getAll(int page, int size) {
        if (page <0) page = 0;

        PageRequest pagination = PageRequest.of(page -1, size);
        
        return this.courseRepository.findAll(pagination)
                .map(courseMapper::courseToCourseResponse);
    }

    @Override
    public CourseResponse getById(Integer id) {
        return courseMapper.courseToCourseResponse(this.find(id));
    }

    @Override
    public CourseResponse create(CourseRequest request) {
        Course newCourse = courseMapper.courseRequestToCourse(request);
        newCourse.setInstructor_id(this.userService.find(request.getTeacherId()));
        return courseMapper.courseToCourseResponse(this.courseRepository.save(newCourse));
    }

    @Override
    public CourseResponse update(Integer id, CourseRequest request) {
        Course courseToUpdate = this.find(id);
        courseMapper.udpateFromCourseRequest(request, courseToUpdate);
        courseToUpdate.setId(id);
        return courseMapper.courseToCourseResponse(this.courseRepository.save(courseToUpdate));
    }

    @Override
    public void delete(Integer id) {
        this.courseRepository.deleteById(id);
    }

    private Course find(int id) {
        return this.courseRepository.findById(id).orElseThrow();
    }
    
}
