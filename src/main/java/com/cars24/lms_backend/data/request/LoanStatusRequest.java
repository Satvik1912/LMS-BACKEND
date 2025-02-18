package com.cars24.lms_backend.data.request;

import com.cars24.lms_backend.data.enums.LoanStatus;

import lombok.Data;

@Data
public class LoanStatusRequest {

    private String userId;

    private String udId;

    private double loanAmount;

    private LoanStatus loanStatus;
}
