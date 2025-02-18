package com.cars24.lms_backend.data.dao.impl;

import com.cars24.lms_backend.data.dao.UserDao;
import com.cars24.lms_backend.data.entities.UsersEntity;
import com.cars24.lms_backend.data.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {

    private final UserRepository userRepository;

    @Override
    public Optional<UsersEntity> findByUsernameDao(String username) {
        log.info("Checking if user exists: {}", username);
        return userRepository.findByUsername(username);
    }

    public void saveUser(UsersEntity user) {
        log.info("Saving user: {}", user.getUsername());
        userRepository.save(user);
        log.info("User saved successfully.");
    }

    @Override
    public Optional<UsersEntity> userResponse(String userId) {
        return userRepository.findById(userId);
    }
}
