package com.cars24.lms_backend.data.response;

import com.cars24.lms_backend.data.enums.LoanType;
import lombok.Data;

@Data
public class CreateLoanDetailsResponse {
    private String imageUrl;
    private LoanType loanType;
    private double rateOfInterest;
}
