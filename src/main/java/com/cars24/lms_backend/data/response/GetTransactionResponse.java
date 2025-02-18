package com.cars24.lms_backend.data.response;

import com.cars24.lms_backend.data.enitities.TransactionEntity;
import com.cars24.lms_backend.data.enums.TransactionType;
import lombok.Data;
import org.springframework.transaction.TransactionStatus;

@Data
public class GetTransactionResponse {
    private double amount;
    private TransactionType transactionType;

}
