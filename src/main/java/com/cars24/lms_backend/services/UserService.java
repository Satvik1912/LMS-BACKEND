package com.cars24.lms_backend.services;

import com.cars24.lms_backend.data.entities.UsersEntity;
import com.cars24.lms_backend.data.req.LoginReq;
import com.cars24.lms_backend.data.req.SignUpReq;
import com.cars24.lms_backend.data.res.ApiResponse;
import org.springframework.stereotype.Service;


@Service
public interface UserService {
    ApiResponse registerUser(SignUpReq user);
    ApiResponse loginUser(LoginReq user);

}
