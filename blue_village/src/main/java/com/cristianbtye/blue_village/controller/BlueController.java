package com.cristianbtye.blue_village.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cristianbtye.blue_village.entity.BluEvent;
import com.cristianbtye.blue_village.service.IService.Iservice;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/blueVillage")
@AllArgsConstructor
public class BlueController {
    
    private final Iservice blueService;

    @GetMapping
    public ResponseEntity<List<BluEvent>>  getAll() {
        return ResponseEntity.ok(this.blueService.getAll());
    }

    @PostMapping("/save")
    public ResponseEntity<BluEvent> create(@RequestBody BluEvent bluEvent) {
        return ResponseEntity.ok(this.blueService.save(bluEvent));
    }
    
    @PostMapping("/update")
    public ResponseEntity<BluEvent> update(@RequestBody BluEvent bluEvent) {
        return ResponseEntity.ok(this.blueService.save(bluEvent));
    }
}
