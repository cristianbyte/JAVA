package com.workshop.library.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workshop.library.api.dto.request.ReservationRequest;
import com.workshop.library.api.dto.response.ReservationResponseFull;
import com.workshop.library.infrastructure.abstract_services.IReservationService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/reservations")
@AllArgsConstructor
public class ReservationController {
    
    @Autowired
    private final IReservationService reservationService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<ReservationResponseFull> get(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(this.reservationService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ReservationResponseFull> create(ReservationRequest request){
        return ResponseEntity.ok(this.reservationService.create(request));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ReservationResponseFull> update(@PathVariable Long id, @RequestBody ReservationRequest request){
        return ResponseEntity.ok(this.reservationService.update(id, request));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.reservationService.delete(id);
        return ResponseEntity.noContent().build();
    }
    

}
