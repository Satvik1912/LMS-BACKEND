package com.cars24.lms_backend.controllers;

import com.cars24.lms_backend.data.entities.UsersEntity;
import com.cars24.lms_backend.data.req.LoginReq;
import com.cars24.lms_backend.data.req.SignUpReq;
import com.cars24.lms_backend.data.res.ApiResponse;
import com.cars24.lms_backend.services.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signUp(@RequestBody SignUpReq user) {
        log.info("Received sign-up request for: {}", user.getUsername());
        ApiResponse response = userService.registerUser(user);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginReq user) {
        log.info("Received login request for: {}", user.getUsername());
        ApiResponse response = userService.loginUser(user);
        return ResponseEntity.ok().body(response);
    }
}
