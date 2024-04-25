package com.cristianbyte.hire_hub_jpa_dto.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity(name="company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(length = 40,nullable = false)
    private String name;
    @Column(length = 60, nullable = false)
    private String location;
    @Column(length = 15, nullable = false)
    private String contact;
    // orphanRemoval -> An object without foreign key it will be deleted.
    @OneToMany(mappedBy = "company" , fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = false)
    @ToString.Exclude // <- exclude method to string
    @EqualsAndHashCode.Exclude
    private List<Vacant> vacants;

}
