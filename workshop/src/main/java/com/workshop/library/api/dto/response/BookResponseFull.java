package com.workshop.library.api.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookResponseFull {

    private String title;
    private String author;
    private int publicationYear;
    private String genre;
    private String isbn;
    private List<ReservationResponse> reservations;
    private List<LoanResponse> loans;
}
