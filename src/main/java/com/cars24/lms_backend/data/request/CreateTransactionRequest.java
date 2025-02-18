package com.cars24.lms_backend.data.request;

import com.cars24.lms_backend.data.enitities.UserDetailsEntity;
import com.cars24.lms_backend.data.enums.TransactionType;
import lombok.Data;

@Data
public class CreateTransactionRequest {
    private TransactionType transactionType;
    private double amount;
    private UserDetailsEntity userDetailsEntity;


}
