package com.cristianbyte.learnify.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.cristianbyte.learnify.api.dto.request.EnrollmentRequest;
import com.cristianbyte.learnify.api.dto.response.EnrollmentResponse;
import com.cristianbyte.learnify.domain.entities.Enrollment;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EnrollmentMapper {
    @Mapping(target = "course_id.id", source="course_id")
    @Mapping(target = "user_id.id", source="user_id")
    Enrollment enrollmentRequestToEnrollment(EnrollmentRequest enrollment);
    
    @Mapping(target = "user_id", source="user_id.id")
    @Mapping(target = "course_id", source="course_id.id")
    @Mapping(target = "user_name", source="user_id.fullname")
    @Mapping(target = "course_name", source="course_id.name")
    EnrollmentResponse enrollmentToEnrollmentResponse(Enrollment enrollment);

    @Mapping(target = "course_id.id", source="course_id")
    @Mapping(target = "user_id.id", source="user_id")
    void udpateFromEnrollmentRequest(EnrollmentRequest enrollmentRequest, @MappingTarget Enrollment enrollment);
}
