package com.cars24.lms_backend.services.impl;

import com.cars24.lms_backend.data.dao.impl.TransactionsDaoImpl;
import com.cars24.lms_backend.data.enitities.TransactionEntity;
import com.cars24.lms_backend.data.response.ApiResponse;
import com.cars24.lms_backend.data.response.GetTransactionResponse;
import com.cars24.lms_backend.services.TransactionsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class TransactionsServiceImpl implements TransactionsService {
    private final TransactionsDaoImpl transactionsDao;
    @Override
    public ResponseEntity<ApiResponse> getTransactions(String userDetailsId) {
        ApiResponse apiResponse=new ApiResponse();

        GetTransactionResponse getTransactionResponse=transactionsDao.getTransactions(userDetailsId);

        Map<String,Object> messageResponse=new HashMap<>();
        messageResponse.put("Amount",getTransactionResponse.getAmount());
        messageResponse.put("Transaction type",getTransactionResponse.getTransactionType());

        apiResponse.setStatusCode(HttpStatus.OK.value());
        apiResponse.setService("Get customer - "+HttpStatus.OK.value());
        apiResponse.setMessage("Get user successful");
        apiResponse.setData(messageResponse);
        apiResponse.setSuccess(true);
        log.info("[in loan details service]:{}",messageResponse);
        return ResponseEntity.ok().body(apiResponse);
    }

    @Override
    public ResponseEntity<ApiResponse> createTransactions(TransactionEntity transactionEntity, String userDetailsId) {
        ApiResponse apiResponse=new ApiResponse();
        transactionsDao.createTransactions(transactionEntity,userDetailsId);
        apiResponse.setMessage("Transactions Details created successfully");
        apiResponse.setData(null);
        apiResponse.setService("Transactions Details - "+HttpStatus.OK.value());
        apiResponse.setStatusCode(HttpStatus.OK.value());
        apiResponse.setSuccess(true);
        log.info("[in create transactions details service]:{}",apiResponse);
        return ResponseEntity.ok().body(apiResponse);
    }

    public List<GetTransactionResponse> getTransactionsByUserDetailsId(String userDetailsId)
    {
        ApiResponse apiResponse=new ApiResponse();
        GetTransactionResponse getTransactionResponse= (GetTransactionResponse) transactionsDao.getTransactionsByUserDetailsId(userDetailsId);
        Map<String,Object> messageResponse=new HashMap<>();
        messageResponse.put("Amount",getTransactionResponse.getAmount());
        messageResponse.put("Transaction type",getTransactionResponse.getTransactionType());

        apiResponse.setStatusCode(HttpStatus.OK.value());
        apiResponse.setService("Get customer - "+HttpStatus.OK.value());
        apiResponse.setMessage("Get user successful");
        apiResponse.setData(messageResponse);
        apiResponse.setSuccess(true);
        log.info("[in loan details service]:{}",messageResponse);
        return (List<GetTransactionResponse>) ResponseEntity.ok().body(apiResponse);
    }
}
