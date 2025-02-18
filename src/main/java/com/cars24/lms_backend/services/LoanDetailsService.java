package com.cars24.lms_backend.services;

import com.cars24.lms_backend.data.request.CreateLoanDetailsRequest;
import com.cars24.lms_backend.data.response.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface LoanDetailsService {
    ResponseEntity<ApiResponse> getLoanDetails(String loanDetailsId);
    ResponseEntity<ApiResponse> createLoanDetails(CreateLoanDetailsRequest createLoanDetailsRequest);

}
