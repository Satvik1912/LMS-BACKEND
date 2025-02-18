package com.cars24.lms_backend.services;

import com.cars24.lms_backend.data.request.LoginReq;
import com.cars24.lms_backend.data.request.SignUpReq;
import com.cars24.lms_backend.data.response.ApiResponse;
import org.springframework.stereotype.Service;


@Service
public interface UserService {
    ApiResponse registerUser(SignUpReq user);
    ApiResponse loginUser(LoginReq user);
    ApiResponse getUser(String userId);

}
