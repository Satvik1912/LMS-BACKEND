package com.cars24.lms_backend.data.repositories;

import com.cars24.lms_backend.data.entities.UserDetailsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserDetailsRepository extends MongoRepository<UserDetailsEntity,String> {
    Optional<UserDetailsEntity> findByUdId(String udId);
    boolean existsByUserId(String userId);


}
