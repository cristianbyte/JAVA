package com.cristianbtye.blue_village.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity(name = "bluEvents")
@Data //Generete Getters and Setters
@AllArgsConstructor // Fully Constructor
// @NoArgsConstructor // Empty Constructor
public class BluEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private LocalDate date;
    private String location;
    private int capacity;
    
    public BluEvent() {}
}
