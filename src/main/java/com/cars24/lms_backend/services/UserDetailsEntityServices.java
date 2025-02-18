package com.cars24.lms_backend.services;

import com.cars24.lms_backend.data.request.CreateUserDetailsRequest;
import com.cars24.lms_backend.data.response.ApiResponse;

public interface UserDetailsEntityServices {

    ApiResponse getUserDetailsService(String userId);
    ApiResponse createUserDetailsService(CreateUserDetailsRequest userDetailsRequest);
}
