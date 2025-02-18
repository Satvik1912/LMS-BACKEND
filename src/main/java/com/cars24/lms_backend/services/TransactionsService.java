package com.cars24.lms_backend.services;

import com.cars24.lms_backend.data.enitities.TransactionEntity;
import com.cars24.lms_backend.data.response.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface TransactionsService {
    ResponseEntity<ApiResponse> getTransactions(String transactionId);
    ResponseEntity<ApiResponse> createTransactions(TransactionEntity transactionEntity, String userDetailsId);
}
