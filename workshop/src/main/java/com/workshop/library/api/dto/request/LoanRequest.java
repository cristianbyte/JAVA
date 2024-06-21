package com.workshop.library.api.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

import com.workshop.library.utils.enums.StatusType;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanRequest {

    @NotNull(message = "Loan date cannot be null")
    @FutureOrPresent(message = "future or present")
    private LocalDateTime loanDate;
    @NotNull(message = "Return date cannot be null")
    @Future(message = "Return date must be in the future")
    private LocalDateTime returnDate;

    @Pattern(regexp = "ACTIVE|INACTIVE", message = "Status must be either 'ACTIVE' or 'INACTIVE'")
    private StatusType status;
    @NotNull(message = "User ID cannot be null")
    private Long user_id;
    @NotNull(message = "Book ID cannot be null")
    private Long book_id;
}
