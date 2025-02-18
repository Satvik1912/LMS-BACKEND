package com.cars24.lms_backend.data.repositories;

import com.cars24.lms_backend.data.entities.UserDetailsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends MongoRepository<UserDetailsEntity, String> {
    UserDetailsEntity findByUserId(String userId);
}
