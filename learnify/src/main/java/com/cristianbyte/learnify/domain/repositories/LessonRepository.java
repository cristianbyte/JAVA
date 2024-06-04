package com.cristianbyte.learnify.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cristianbyte.learnify.domain.entities.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Integer>{
}
