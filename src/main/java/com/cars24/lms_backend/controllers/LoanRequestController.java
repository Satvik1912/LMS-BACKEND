package com.cars24.lms_backend.controllers;


import com.cars24.lms_backend.data.request.LoanRequestEntityRequest;
import com.cars24.lms_backend.data.response.ApiResponse;
import com.cars24.lms_backend.services.impl.LoanRequestServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loan-request")
@RequiredArgsConstructor
@Service
public class LoanRequestController {

    private final LoanRequestServiceImpl loanRequestService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createLoanRequest(@RequestBody LoanRequestEntityRequest loanRequest){

        ApiResponse response = loanRequestService.createLoanRequestService(loanRequest);
        return ResponseEntity.ok().body(response);

    }
}
