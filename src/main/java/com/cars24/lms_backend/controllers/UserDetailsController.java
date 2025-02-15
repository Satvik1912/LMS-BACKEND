package com.cars24.lms_backend.controllers;

import com.cars24.lms_backend.data.req.CreateUserDetailsRequest;
import com.cars24.lms_backend.data.res.ApiResponse;
import com.cars24.lms_backend.data.res.UserDetailsResponse;
import com.cars24.lms_backend.services.impl.UserDetailsEntityServicesImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user-details")
@RequiredArgsConstructor
@Service
public class UserDetailsController {

    private final UserDetailsEntityServicesImpl userDetailsServices;


    @GetMapping("/get-user/{userId}")
    public ResponseEntity<ApiResponse> getDetails(@PathVariable String userId){
        ApiResponse getUserEntityDetails=  userDetailsServices.getUserDetailsService(userId);
        return ResponseEntity.ok().body(getUserEntityDetails);
    }


    @PostMapping("/add-details")
    public ResponseEntity<ApiResponse> createUserDetails(@RequestBody CreateUserDetailsRequest userDetailsRequest){
        ApiResponse createUserDetailsResponse=userDetailsServices.createUserDetailsService(userDetailsRequest);
        return ResponseEntity.ok().body(createUserDetailsResponse);
    }
}
