package com.cars24.lms_backend.services.impl;

import com.cars24.lms_backend.data.dao.impl.UserDaoImpl;
import com.cars24.lms_backend.data.entities.UsersEntity;
import com.cars24.lms_backend.data.request.LoginReq;
import com.cars24.lms_backend.data.request.SignUpReq;
import com.cars24.lms_backend.data.response.ApiResponse;
import com.cars24.lms_backend.data.response.UserResponse;
import com.cars24.lms_backend.services.UserService;
import com.cars24.lms_backend.utility.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserDaoImpl userDao;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public ApiResponse registerUser(SignUpReq user) {
        Optional<UsersEntity> existingUser = userDao.findByUsernameDao(user.getUsername());

        if (existingUser.isPresent()) {
            log.info("User already exists: {}", user.getUsername());
            return  generateApiResponse(HttpStatus.BAD_REQUEST.value(),false, "User Already Exists", null,"APPUSER");
        }

        // Encode the password before saving
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setName(user.getName());
        usersEntity.setUsername(user.getUsername());
        usersEntity.setPassword(user.getPassword());
        usersEntity.setRoles(user.getRole());
        usersEntity.setPhone(user.getPhone());

        userDao.saveUser(usersEntity);

        return  generateApiResponse(HttpStatus.OK.value(),true, "User registered successfully", null,"APPUSER");
    }

    @Override
    public ApiResponse loginUser(LoginReq user) {
        Optional<UsersEntity> existingUser = userDao.findByUsernameDao(user.getUsername());

        if (existingUser.isPresent()) {
            // Validate the password
            if ( passwordEncoder.matches(user.getPassword(), existingUser.get().getPassword())) {
                String userId= existingUser.get().getId();
                String token = jwtUtil.generateToken(user.getUsername());
                Map<String, Object> responseData = new HashMap<>();
                responseData.put("token", token);
                responseData.put("userId",userId);
                return generateApiResponse(HttpStatus.OK.value(), true, "User Logged in successfully",responseData ,"APPUSER");
            } else {
                throw new RuntimeException("Invalid Username or Password");
            }
        }
        return generateApiResponse(HttpStatus.BAD_REQUEST.value(),false, "User doesn't exists. Please SignUp", null,"APPUSER");
    }

    public ApiResponse getUser(String userId){

        Optional<UsersEntity> userEntity = userDao.userResponse(userId);

        UserResponse userResponse = new UserResponse();
        if(userEntity.isPresent()){
            userResponse.setName(userEntity.get().getName());
            userResponse.setPhone(userEntity.get().getPhone());
            userResponse.setUsername(userEntity.get().getUsername());
            return generateApiResponse(HttpStatus.OK.value(), true, "User fetched successfully",userResponse ,"APPUSER");
        }
        return generateApiResponse(HttpStatus.BAD_REQUEST.value(),false, "User doesn't exists.", null,"APPUSER");


    }

    private ApiResponse generateApiResponse(int statusCode, boolean success, String message, Object data, String service){
        ApiResponse response =new ApiResponse();
        response.setStatusCode(statusCode);
        response.setSuccess(success);
        response.setMessage(message);
        response.setService(service);
        response.setData(data);
        return response;
    }
}
