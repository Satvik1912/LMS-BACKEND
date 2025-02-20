package com.cars24.lms_backend.data.repositories;

import com.cars24.lms_backend.data.entities.LoanRequestEntity;
import com.cars24.lms_backend.data.entities.UserDetailsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LoanRequestRepository extends MongoRepository<LoanRequestEntity,String> {
    LoanRequestEntity findByUserId(String userId);
}
