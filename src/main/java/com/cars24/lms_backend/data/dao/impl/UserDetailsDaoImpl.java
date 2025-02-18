package com.cars24.lms_backend.data.dao.impl;

import com.cars24.lms_backend.data.dao.UserDetailsDao;
import com.cars24.lms_backend.data.entities.UserDetailsEntity;
import com.cars24.lms_backend.data.entities.UsersEntity;
import com.cars24.lms_backend.data.repositories.UserDetailsRepository;
import com.cars24.lms_backend.data.repositories.UserRepository;
import com.cars24.lms_backend.data.request.CreateUserDetailsRequest;
import com.cars24.lms_backend.data.response.UserDetailsResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserDetailsDaoImpl implements UserDetailsDao {

    private final UserDetailsRepository userDetailsRepository;
    private final UserRepository userRepo;

    @Override
    public UserDetailsResponse getUserDetailsDao(String userId) {

        UserDetailsEntity userDetailsEntity=userDetailsRepository.findByUserId(userId);

        ObjectMapper objectMapper =new ObjectMapper();
        return objectMapper.convertValue(userDetailsEntity, UserDetailsResponse.class);
    }

    @Override
    public UserDetailsEntity createUserDetailsDao(CreateUserDetailsRequest userDetailsRequest) {

        // I have to check if userId exists in user collection
        //also we have to ensure that we don't insert multiple documents of same user (to be implemented later)

        Optional<UsersEntity> user = userRepo.findById(userDetailsRequest.getUserId());

        if(user.isPresent()){

            ObjectMapper objectMapper =new ObjectMapper();
            UserDetailsEntity userDetails = objectMapper.convertValue(userDetailsRequest,UserDetailsEntity.class);
            userDetails.setDocuments(new String[0]);

            return userDetailsRepository.save(userDetails);
        }else{
            throw new RuntimeException("User doesn't exists");
        }
    }
}
