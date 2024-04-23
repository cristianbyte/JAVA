package com.cristianbtye.andys_toy_store.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cristianbtye.andys_toy_store.entities.Toy;
import com.cristianbtye.andys_toy_store.service.service_abstract.IToyService;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/v1/toys")
@AllArgsConstructor
public class ToyController {

    private final IToyService toyService;

    @GetMapping
    public ResponseEntity<List<Toy>>  getAll() {
        return ResponseEntity.ok(this.toyService.getAll());
    }

    @DeleteMapping("/delete/{id}")
    public Boolean delete(@PathVariable Long id){
        return this.toyService.delete(id);
    }

    @GetMapping("/search/{id}")
    public Toy searchById(@PathVariable Long id){
         return this.toyService.findById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<Toy> createToy(@RequestBody Toy toy) {
        return ResponseEntity.ok(toyService.save(toy));
    }

    @PutMapping("/update")
    public ResponseEntity<Toy> updateToy(@RequestBody Toy toy) {
        return ResponseEntity.ok(toyService.save(toy));
    }
}
