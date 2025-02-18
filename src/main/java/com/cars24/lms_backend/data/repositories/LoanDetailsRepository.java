package com.cars24.lms_backend.data.repositories;

import com.cars24.lms_backend.data.enitities.LoanDetailsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LoanDetailsRepository extends MongoRepository<LoanDetailsEntity,String> {
}
