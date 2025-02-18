package com.cars24.lms_backend.services;

import com.cars24.lms_backend.data.request.LoanStatusRequest;
import com.cars24.lms_backend.data.response.ApiResponse;
import com.cars24.lms_backend.data.response.LoanStatusResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public interface LoanStatusService {
    ResponseEntity<ApiResponse> createLoanStatus(@Valid LoanStatusRequest request);
    List<LoanStatusResponse> getLoanStatusByUserId(String userId);

    ResponseEntity<ApiResponse> updateLoanStatus(String userId, LoanStatusRequest request);
    void deleteLoanStatus(String id);
}
