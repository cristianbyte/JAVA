package com.cristianbyte.learnify.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cristianbyte.learnify.api.dto.request.CourseRequest;
import com.cristianbyte.learnify.api.dto.response.CourseResponse;
import com.cristianbyte.learnify.infraestructure.abstract_service.ICourseService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;



@RestController
@RequestMapping(path = "/course")
@AllArgsConstructor
@Tag(name="Course")
public class CourseController {

    @Autowired
    private final ICourseService courseService;

    @GetMapping
    public ResponseEntity<Page<CourseResponse>> getAll(
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "10") int size
    ){
        return ResponseEntity.ok(this.courseService.getAll(page, size));
    }

    @PostMapping
    public ResponseEntity<CourseResponse> create(@Validated @RequestBody CourseRequest course) {
        return ResponseEntity.ok(this.courseService.create(course));
    }
    
    @PutMapping(path = "/{id}")
    public ResponseEntity<CourseResponse> update(@PathVariable Integer id, @Validated @RequestBody CourseRequest course) {
        return ResponseEntity.ok(this.courseService.update(id, course));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CourseResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(this.courseService.getById(id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        this.courseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
