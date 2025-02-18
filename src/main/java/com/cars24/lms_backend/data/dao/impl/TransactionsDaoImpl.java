package com.cars24.lms_backend.data.dao.impl;

import com.cars24.lms_backend.data.dao.TransactionsDao;
import com.cars24.lms_backend.data.enitities.TransactionEntity;
import com.cars24.lms_backend.data.enitities.UserDetailsEntity;
import com.cars24.lms_backend.data.enums.TransactionType;
import com.cars24.lms_backend.data.repositories.TransactionRepository;
import com.cars24.lms_backend.data.repositories.UserDetailsRepository;

import com.cars24.lms_backend.data.response.GetTransactionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionsDaoImpl implements TransactionsDao {
    private final MongoTemplate mongoTemplate;
    private final UserDetailsRepository userDetailsRepository;

    private final TransactionRepository transactionRepository;

    @Override
    public GetTransactionResponse getTransactions(String userDetailsId) {
        Query query = new Query(Criteria.where("userDetails._id").is(userDetailsId));
        TransactionEntity transactionEntity = mongoTemplate.findOne(query, TransactionEntity.class);
        if(transactionEntity==null) throw new RuntimeException("Transaction not found for UserDetailsId: "+userDetailsId);
       GetTransactionResponse getTransactionResponse=new GetTransactionResponse();
        getTransactionResponse.setAmount(transactionEntity.getAmount());
        getTransactionResponse.setTransactionType(TransactionType.valueOf(String.valueOf(transactionEntity.getTransactionType())));
        log.info("[in loan details dao]:{}",getTransactionResponse);
        return  getTransactionResponse;
    }



    @Override
    public TransactionEntity createTransactions(TransactionEntity transactionEntity, String userDetailsId) {
        UserDetailsEntity userDetails=userDetailsRepository.findById(userDetailsId) .orElseThrow(()-> new RuntimeException("User Details not found for id: "+userDetailsId));

//        TransactionEntity transactionEntity=new TransactionEntity();

//        transactionEntity.setTransactionType(createTransactionRequest.getTransactionType());
//        transactionEntity.setAmount(createTransactionRequest.getAmount());
        transactionEntity.setUserDetails(userDetails);

//        Query query = new Query(Criteria.where("_id").is(userDetailsId));
//        UserDetailsEntity userDetailsEntity = mongoTemplate.findOne(query, UserDetailsEntity.class);
        transactionRepository.save(transactionEntity);
        log.info("[in create transaction details dao]:{}",transactionEntity);
        return transactionEntity;
    }

    public List<GetTransactionResponse> getTransactionsByUserDetailsId(String userDetailsId)
    {
        List<TransactionEntity> transactions=transactionRepository.findByUserDetailsId(userDetailsId);
        if(transactions.isEmpty()) throw new RuntimeException("No transactions found for userDetails Id: "+userDetailsId);
        return transactions.stream().map(transaction -> {
            GetTransactionResponse resp=new GetTransactionResponse();
            resp.setTransactionType(transaction.getTransactionType());
            resp.setAmount(transaction.getAmount());
            return resp;
        }).collect(Collectors.toList());
    }
}
