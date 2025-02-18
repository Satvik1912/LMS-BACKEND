package com.cars24.lms_backend.controllers;

import com.cars24.lms_backend.data.request.LoanStatusRequest;
import com.cars24.lms_backend.data.response.ApiResponse;
import com.cars24.lms_backend.data.response.LoanStatusResponse;
import com.cars24.lms_backend.services.LoanStatusService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loan-status")
@Validated
public class LoanStatusController {

    @Autowired
    private LoanStatusService loanStatusService;

    @PostMapping
    public ResponseEntity<ApiResponse>  createLoanStatus(@Valid @RequestBody LoanStatusRequest request) {
        return loanStatusService.createLoanStatus(request);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<LoanStatusResponse>> getLoanStatus(@PathVariable String userId) {
        List<LoanStatusResponse> responseList = loanStatusService.getLoanStatusByUserId(userId);
        return responseList.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(responseList);
    }

    @PutMapping("/{udId}")
    public ResponseEntity<ApiResponse> updateLoanStatus(@Valid @PathVariable String udId, @RequestBody LoanStatusRequest request) {
        return loanStatusService.updateLoanStatus(udId, request);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoanStatus(@PathVariable String id) {
        loanStatusService.deleteLoanStatus(id);
        return ResponseEntity.noContent().build();
    }
}
