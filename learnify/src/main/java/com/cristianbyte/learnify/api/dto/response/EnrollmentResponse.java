package com.cristianbyte.learnify.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentResponse {
    private int id;
    private String date;
    private int user_id;
    private String user_name;
    private int course_id;
    private String course_name;
}
