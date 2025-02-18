package com.cars24.lms_backend.data.repositories;

import com.cars24.lms_backend.data.entities.LoanStatusEntity;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanStatusRepository extends MongoRepository<LoanStatusEntity,String> {
    List<LoanStatusEntity> findByUserId(String userId);

    Optional<LoanStatusEntity> findByUdId(String udId);

    boolean existsByUdId(String udId);
}
