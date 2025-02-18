package com.cars24.lms_backend.controllers;

import com.cars24.lms_backend.data.enitities.TransactionEntity;
import com.cars24.lms_backend.data.response.ApiResponse;
import com.cars24.lms_backend.services.impl.TransactionsServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
@Slf4j
@Validated
public class TransactionController {
    private final TransactionsServiceImpl transactionService;

    @GetMapping("/{userDetailsId}")
    public ResponseEntity<ApiResponse> getTransactions(@Valid @PathVariable String userDetailsId)
    {
        return transactionService.getTransactions(userDetailsId);

    }

    @PostMapping("/addTransaction/{userDetailsId}")
    public ResponseEntity<ApiResponse> createTransactions(@Valid @PathVariable String userDetailsId, @RequestBody TransactionEntity transactionEntity)
    {
        transactionService.createTransactions(transactionEntity,userDetailsId);
        log.info("[in create transactions]:{}",transactionEntity);
        return transactionService.createTransactions(transactionEntity,userDetailsId);
    }
}
