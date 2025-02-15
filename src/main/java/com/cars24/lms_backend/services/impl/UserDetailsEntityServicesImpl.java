package com.cars24.lms_backend.services.impl;

import com.cars24.lms_backend.data.dao.impl.UserDetailsDaoImpl;
import com.cars24.lms_backend.data.entities.UserDetailsEntity;
import com.cars24.lms_backend.data.req.CreateUserDetailsRequest;
import com.cars24.lms_backend.data.res.ApiResponse;
import com.cars24.lms_backend.data.res.UserDetailsResponse;
import com.cars24.lms_backend.services.UserDetailsEntityServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class UserDetailsEntityServicesImpl implements UserDetailsEntityServices {


    private final UserDetailsDaoImpl userDetailsDao;


    @Override
    public UserDetailsResponse getUserDetailsService(String userId) {
        //it should call a function in dao


        return userDetailsDao.getUserDetailsDao(userId);
    }

    public ApiResponse createUserDetailsService(CreateUserDetailsRequest userDetailsRequest){

        ApiResponse createUserDetailsServiceRes=new ApiResponse();

        //call to dao
        UserDetailsEntity userCreated =userDetailsDao.createUserDetailsDao(userDetailsRequest);

        createUserDetailsServiceRes.setStatusCode(HttpStatus.OK.value());
        createUserDetailsServiceRes.setSuccess(true);
        createUserDetailsServiceRes.setMessage("User Details Inserted Successfully");
        createUserDetailsServiceRes.setService("APPUSERDET"+ HttpStatus.OK.value());
        createUserDetailsServiceRes.setData(userCreated);

        return createUserDetailsServiceRes;
    }
}
