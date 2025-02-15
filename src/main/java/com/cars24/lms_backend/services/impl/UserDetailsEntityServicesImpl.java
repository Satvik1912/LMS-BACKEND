package com.cars24.lms_backend.services.impl;

import com.cars24.lms_backend.data.dao.impl.UserDetailsDaoImpl;
import com.cars24.lms_backend.data.entities.UserDetailsEntity;
import com.cars24.lms_backend.data.req.CreateUserDetailsRequest;
import com.cars24.lms_backend.data.res.ApiResponse;
import com.cars24.lms_backend.data.res.UserDetailsResponse;
import com.cars24.lms_backend.services.UserDetailsEntityServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class UserDetailsEntityServicesImpl implements UserDetailsEntityServices {


    private final UserDetailsDaoImpl userDetailsDao;


    @Override
    public ApiResponse getUserDetailsService(String userId) {
        //it should call a function in dao
        UserDetailsResponse userDetailsResponse=userDetailsDao.getUserDetailsDao(userId);

        if(userDetailsResponse!=null){
            ApiResponse getUserDetailsResponse=new ApiResponse();

            getUserDetailsResponse.setData(userDetailsResponse);
            getUserDetailsResponse.setService("APPUSRDET"+HttpStatus.OK.value());
            getUserDetailsResponse.setSuccess(true);
            getUserDetailsResponse.setMessage("User Details fetched Successfully");
            getUserDetailsResponse.setStatusCode(HttpStatus.OK.value());

            return getUserDetailsResponse;
        }else{
            throw new RuntimeException("User details with user id "+ userId + "doesn't exists");
        }
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
