package com.cristianbyte.learnify.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cristianbyte.learnify.domain.entities.Course;

public interface CourseRepository extends JpaRepository<Course, Integer>{
    
}
