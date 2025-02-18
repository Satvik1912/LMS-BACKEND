package com.cars24.lms_backend.data.dao;


import com.cars24.lms_backend.data.entities.UserDetailsEntity;
import com.cars24.lms_backend.data.request.CreateUserDetailsRequest;
import com.cars24.lms_backend.data.response.UserDetailsResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserDetailsDao {
    UserDetailsResponse getUserDetailsDao(String userId);
    UserDetailsEntity createUserDetailsDao(CreateUserDetailsRequest userDetailsRequest);
}
