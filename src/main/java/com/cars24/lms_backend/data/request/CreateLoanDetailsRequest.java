package com.cars24.lms_backend.data.request;

import com.cars24.lms_backend.data.enums.LoanType;
import jakarta.validation.Valid;
import lombok.Data;

@Data
public class CreateLoanDetailsRequest {
    @Valid
    private LoanType loanType;

    @Valid
    private double rateOfInterest;

    @Valid
    private String imageUrl;

}
