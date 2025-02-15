package com.cars24.lms_backend.services;

import com.cars24.lms_backend.data.req.CreateUserDetailsRequest;
import com.cars24.lms_backend.data.res.ApiResponse;
import com.cars24.lms_backend.data.res.UserDetailsResponse;

public interface UserDetailsEntityServices {

    ApiResponse getUserDetailsService(String userId);
    ApiResponse createUserDetailsService(CreateUserDetailsRequest userDetailsRequest);
}
