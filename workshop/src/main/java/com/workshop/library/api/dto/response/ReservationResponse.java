package com.workshop.library.api.dto.response;

import java.time.LocalDateTime;

import com.workshop.library.utils.enums.StatusType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResponse {

    private Long id;
    
    private LocalDateTime reservationDate;

    private StatusType status;

    private BookResponse book;

}
