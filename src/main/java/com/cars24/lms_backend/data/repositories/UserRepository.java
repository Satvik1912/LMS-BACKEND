package com.cars24.lms_backend.data.repositories;

import com.cars24.lms_backend.data.entities.UsersEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UsersEntity , String> {
    Optional<UsersEntity> findByUsername(String username);
}
