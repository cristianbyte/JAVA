package com.cristianbyte.learnify.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LessonRequest {
    @NotBlank(message = "title of the lesson is required")
    private String title;
    @NotBlank(message = "content of the lesson is required")
    private String content;
    @NotNull(message = "the id of the course is required")
    private int course_id;
}
