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

import com.cristianbyte.learnify.api.dto.request.EnrollmentRequest;
import com.cristianbyte.learnify.api.dto.response.EnrollmentResponse;
import com.cristianbyte.learnify.infraestructure.abstract_service.IEnrollmentService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/enrollment")
@AllArgsConstructor
@Tag(name="Enrollmest")
public class EnrollmentController {

    @Autowired
    private final IEnrollmentService enrollmentService;

    @GetMapping
    public ResponseEntity<Page<EnrollmentResponse>> getALL(
        @RequestParam(defaultValue = "1") int page, 
        @RequestParam(defaultValue = "10") int size
    ){
    return ResponseEntity.ok(this.enrollmentService.getAll(page, size));
    }

    //get enrollmen by id
    @GetMapping(path = "/{id}")
    public ResponseEntity<EnrollmentResponse> getById(@PathVariable Integer id){
        return ResponseEntity.ok(this.enrollmentService.getById(id));
    }

    //create enrollment
    @PostMapping
    public ResponseEntity<EnrollmentResponse> create(@Validated @RequestBody EnrollmentRequest enrollment) {
        return ResponseEntity.ok(this.enrollmentService.create(enrollment));
    }    

    //update enrollment
    @PutMapping(path = "/{id}")
    public ResponseEntity<EnrollmentResponse> update(@PathVariable Integer id, @Validated @RequestBody EnrollmentRequest enrollment){
        return ResponseEntity.ok(this.enrollmentService.update(id, enrollment));
    }

    //delete enrollment
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        this.enrollmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
