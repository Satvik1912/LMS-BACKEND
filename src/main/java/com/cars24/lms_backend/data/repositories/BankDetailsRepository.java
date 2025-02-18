package com.cars24.lms_backend.data.repositories;

import com.cars24.lms_backend.data.entities.BankDetailsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BankDetailsRepository extends MongoRepository <BankDetailsEntity,String>{
    Optional<BankDetailsEntity> findByUserId(String userId);
}
