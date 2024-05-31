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

import com.cristianbyte.learnify.api.dto.request.UserRequest;
import com.cristianbyte.learnify.api.dto.response.UserResponse;
import com.cristianbyte.learnify.infraestructure.abstract_service.IUserService;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping(path = "/users")
@AllArgsConstructor
public class UserController {
    @Autowired
    private final IUserService userService;

    @GetMapping
    public ResponseEntity<Page<UserResponse>> getAll(
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "10") int size
    ){
        return ResponseEntity.ok(this.userService.getAll(page , size));
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@Validated @RequestBody UserRequest user) {
        return ResponseEntity.ok(this.userService.create(user));
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getMethodName(@PathVariable String id) {
        try {
            return ResponseEntity.ok(this.userService.getById(id));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@Validated @RequestBody UserRequest user, @PathVariable String id) {
        return ResponseEntity.ok(this.userService.update(id,user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        this.userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
