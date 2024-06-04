package com.cristianbyte.learnify.api.dto.request;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequest {
    @NotBlank(message = "name of the course is required")
    private String name;
    @NotBlank(message="description of the course is required")
    private String description;
    @NotBlank(message="teacher of the course is required")
    @Size(max = 6, min=6, message = "only 6 characters")
    private String teacherId;
    private List<LessonRequest> lessons;
}