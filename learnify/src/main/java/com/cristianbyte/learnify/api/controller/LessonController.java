package com.cristianbyte.learnify.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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

import com.cristianbyte.learnify.api.dto.request.LessonRequest;
import com.cristianbyte.learnify.api.dto.response.LessonResponse;
import com.cristianbyte.learnify.infraestructure.abstract_service.ILessonService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;


@RestController
@RequestMapping(path = "/lessons")
@AllArgsConstructor
@Tag(name = "Lessons")
public class LessonController {
    @Autowired
    private final ILessonService lessonService;

    @GetMapping
    public ResponseEntity<Page<LessonResponse>> getAll(
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(this.lessonService.getAll(page, size));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<LessonResponse> getById(Integer id) {
        return ResponseEntity.ok(this.lessonService.getById(id));
    }

    @PostMapping
    public ResponseEntity<LessonResponse> create(@Validated @RequestBody LessonRequest lesson) {
        return ResponseEntity.ok(this.lessonService.create(lesson));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<LessonResponse> update(@PathVariable Integer id, @Validated @RequestBody LessonRequest lesson) {
        return ResponseEntity.ok(this.lessonService.update(id, lesson));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        this.lessonService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
