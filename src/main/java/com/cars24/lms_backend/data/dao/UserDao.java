package com.cars24.lms_backend.data.dao;

import com.cars24.lms_backend.data.entities.UsersEntity;
import com.cars24.lms_backend.data.req.SignUpReq;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public interface UserDao {
    Optional<UsersEntity> findByUsernameDao(String username);
    void saveUser(UsersEntity user);
}
