package com.cristianbyte.learnify.api.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponse {
    private int id;
    private String name;
    private String description;
    private String teacherName;
    private List<LessonResponse> lessons;
}