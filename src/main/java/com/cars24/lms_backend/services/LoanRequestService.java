package com.cars24.lms_backend.services;

import com.cars24.lms_backend.data.request.LoanRequestEntityRequest;
import com.cars24.lms_backend.data.response.ApiResponse;

public interface LoanRequestService {
    ApiResponse createLoanRequestService(LoanRequestEntityRequest loanRequestEntityRequest);
}
