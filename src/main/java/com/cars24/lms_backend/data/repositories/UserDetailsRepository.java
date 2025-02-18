package com.cars24.lms_backend.data.repositories;

import com.cars24.lms_backend.data.enitities.UserDetailsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserDetailsRepository extends MongoRepository<UserDetailsEntity,String> {
}
