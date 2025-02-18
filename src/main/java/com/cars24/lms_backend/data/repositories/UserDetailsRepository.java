package com.cars24.lms_backend.data.repositories;

import com.cars24.lms_backend.data.entities.UserDetailsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsRepository extends MongoRepository<UserDetailsEntity, String> {
    UserDetailsEntity findByUserId(String userId);
    Optional<UserDetailsEntity> findByUdId(String udId);

    boolean existsByUserId(String userId);
}
