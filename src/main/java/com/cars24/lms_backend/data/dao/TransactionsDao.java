package com.cars24.lms_backend.data.dao;

import com.cars24.lms_backend.data.enitities.TransactionEntity;
import com.cars24.lms_backend.data.response.GetTransactionResponse;

public interface TransactionsDao {
    GetTransactionResponse getTransactions(String userDetailsId);
    TransactionEntity createTransactions(TransactionEntity transactionEntity, String userDetailsId);
}
