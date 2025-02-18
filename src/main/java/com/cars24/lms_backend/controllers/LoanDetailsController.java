package com.cars24.lms_backend.controllers;

import com.cars24.lms_backend.data.request.CreateLoanDetailsRequest;
import com.cars24.lms_backend.data.response.ApiResponse;
import com.cars24.lms_backend.services.impl.LoanDetailsServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/loan-details")
@RequiredArgsConstructor
@Validated
public class LoanDetailsController {
    @Autowired
    private final LoanDetailsServiceImpl loanDetailsService;

    @GetMapping("/{loanDetailsId}")
    public ResponseEntity<ApiResponse> getLoanDetails(@Valid @PathVariable String loanDetailsId) {

        return loanDetailsService.getLoanDetails(loanDetailsId);
    }

    @PostMapping("/addLoan")
    public ResponseEntity<ApiResponse> createLoanDetails(@Valid @RequestBody CreateLoanDetailsRequest createLoanDetailsRequest)
    {
        log.info("[in create loan details controller]:{}",createLoanDetailsRequest);
        return loanDetailsService.createLoanDetails(createLoanDetailsRequest);

    }


}



