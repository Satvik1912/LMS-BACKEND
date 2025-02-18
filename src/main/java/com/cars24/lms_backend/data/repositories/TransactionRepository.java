package com.cars24.lms_backend.data.repositories;

import com.cars24.lms_backend.data.enitities.LoanDetailsEntity;
import com.cars24.lms_backend.data.enitities.TransactionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransactionRepository extends MongoRepository<TransactionEntity,String> {
    List<TransactionEntity> findByUserDetailsId(String userDetailsId);

}
