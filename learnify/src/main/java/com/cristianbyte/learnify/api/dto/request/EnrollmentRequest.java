package com.cristianbyte.learnify.api.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentRequest {
    @Size(min=6,max=6, message="the number of characters required (6)")
    private String user_id;
    @NotNull(message="course_id is required") 
    private int course_id;
}
