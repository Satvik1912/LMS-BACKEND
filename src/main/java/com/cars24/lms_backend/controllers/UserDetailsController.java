package com.cars24.lms_backend.controllers;

import com.cars24.lms_backend.data.req.CreateUserDetailsRequest;
import com.cars24.lms_backend.data.res.ApiResponse;
import com.cars24.lms_backend.data.res.UserDetailsResponse;
import com.cars24.lms_backend.services.impl.UserDetailsServicesImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Service
public class UserDetailsController {

    private final UserDetailsServicesImpl userDetailsServices;

    @GetMapping("/{userId}")
    public ResponseEntity<UserDetailsResponse> getDetails(@PathVariable String userId){
        UserDetailsResponse serviceLayer=  userDetailsServices.getUserDetailsService(userId);
        return ResponseEntity.ok().body(serviceLayer);
    }

    @PostMapping("/createUserDetails")
    public ResponseEntity<ApiResponse> createUserDetails(@RequestBody CreateUserDetailsRequest userDetailsRequest){
        ApiResponse createUserDetailsResponse=userDetailsServices.createUserDetailsService(userDetailsRequest);
        return ResponseEntity.ok().body(createUserDetailsResponse);
    }
}
