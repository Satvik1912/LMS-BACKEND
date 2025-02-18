package com.cars24.lms_backend.services;

import com.cars24.lms_backend.data.request.BankDetailsRequest;
import com.cars24.lms_backend.data.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public interface BankDetailsService {

   ResponseEntity<ApiResponse> createBankDetails(@Valid BankDetailsRequest request);
   ResponseEntity<ApiResponse> getBankDetails(String userId);
}
