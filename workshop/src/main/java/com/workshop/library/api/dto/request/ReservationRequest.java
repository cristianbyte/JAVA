package com.workshop.library.api.dto.request;

import com.workshop.library.utils.enums.StatusType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequest {

    private StatusType status;

    private Long userId;

    private Long bookId;

}
