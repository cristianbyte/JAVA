package com.cristianbyte.learnify.domain.entities;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

import com.cristianbyte.learnify.util.enums.RoleType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class User {
    @Id
    @Column(length = 8)
    private String id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String fullname;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private RoleType role;

    public User() {
        this.id = generateShortId();
    }

    private String generateShortId() {
        return RandomStringUtils.randomAlphanumeric(6);
    }

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "user_id")
    private List<Submission> submissions;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "user_id")
    private List<Enrollment> enrollments;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "instructor_id")
    private List<Course> courses;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "sender_id")
    private List<Message> sended_messages ;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "receiver_id")
    private List<Message> received_messages;
}
