package com.cars24.lms_backend.data.response;


import com.cars24.lms_backend.data.enums.LoanStatus;
import lombok.Data;

@Data
public class LoanStatusResponse {
    private String id;
    private String udId;
    private String userId;
    private int loanAmount;
    private LoanStatus loanStatus;
}
