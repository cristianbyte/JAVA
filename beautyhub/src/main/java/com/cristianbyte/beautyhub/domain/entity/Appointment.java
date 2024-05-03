package com.cristianbyte.beautyhub.domain.entity;

import java.security.Timestamp;
import java.sql.Time;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "appointments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp time;
    @Temporal(TemporalType.TIME)
    private Time duration;
    @Column(nullable = false, length = 255)
    private String description;
    @ManyToOne(mappedBy = "client", fetch = FetchType.EAGER, cascade =CascadeType.Is )
    private int client; 
}
