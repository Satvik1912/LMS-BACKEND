package com.cars24.lms_backend.data.request;

import com.cars24.lms_backend.data.enums.LoanStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoanStatusRequest {
    @NotNull(message = "User ID cannot be null")
    private String userId;

    private String udId;

    @Min(value = 50000, message = "Loan amount must be at least 50,000")
    private double loanAmount;

    @NotNull(message = "Loan status cannot be null")
    private LoanStatus loanStatus;
}
