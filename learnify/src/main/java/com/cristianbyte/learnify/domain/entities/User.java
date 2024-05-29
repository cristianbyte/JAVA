package com.cristianbyte.learnify.domain.entities;

import com.cristianbyte.learnify.util.enums.RoleType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;


import org.apache.commons.lang3.RandomStringUtils;

@Entity
@Getter
@AllArgsConstructor
public class User {
    @Id
    private String Id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private RoleType role;

    public User() {
        this.Id = generateShortId();
    }

    private String generateShortId() {
        return RandomStringUtils.randomAlphanumeric(6);
    }
}
