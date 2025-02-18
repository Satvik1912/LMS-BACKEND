package com.cars24.lms_backend.controllers;

import com.cars24.lms_backend.data.request.BankDetailsRequest;
import com.cars24.lms_backend.data.response.ApiResponse;
import com.cars24.lms_backend.services.BankDetailsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bank-details")
@RequiredArgsConstructor
@Validated
public class BankDetailsController {

    private final BankDetailsService bankDetailsService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createBankDetails(@Valid @RequestBody BankDetailsRequest request) {
        return bankDetailsService.createBankDetails(request);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse> getBankDetails(@PathVariable String userId) {
        return bankDetailsService.getBankDetails(userId);  // Pass userId as a parameter
    }

}
