package com.cars24.lms_backend.data.dao.impl;

import com.cars24.lms_backend.data.dao.UserDetailsDao;
import com.cars24.lms_backend.data.entities.UserDetailsEntity;
import com.cars24.lms_backend.data.repositories.UserDetailsRepository;
import com.cars24.lms_backend.data.req.CreateUserDetailsRequest;
import com.cars24.lms_backend.data.res.UserDetailsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserDetailsDaoImpl implements UserDetailsDao {

    private final UserDetailsRepository userDetailsRepository;

    @Override
    public UserDetailsResponse getUserDetailsDao(String userId) {
        UserDetailsEntity userDetailsEntity=userDetailsRepository.findByUserId(userId);
        UserDetailsResponse userDetailsResponse=new UserDetailsResponse();
        userDetailsResponse.setUserId(userDetailsEntity.getUserId());
        userDetailsResponse.setAadhar(userDetailsEntity.getAadhar());
        userDetailsResponse.setAddress(userDetailsEntity.getAddress());
        userDetailsResponse.setCollateral(userDetailsEntity.getCollateral());
        userDetailsResponse.setPanNo(userDetailsEntity.getPanNo());
        userDetailsResponse.setIncomeSource(userDetailsEntity.getIncomeSource());
        userDetailsResponse.setSalary(userDetailsEntity.getSalary());
        userDetailsResponse.setIncomeType(userDetailsEntity.getIncomeType());
        userDetailsResponse.setId(userDetailsEntity.getId());
        return userDetailsResponse;
    }

    @Override
    public UserDetailsEntity createUserDetailsDao(CreateUserDetailsRequest userDetailsRequest) {

        UserDetailsEntity userDetails=new UserDetailsEntity();

        userDetails.setUserId(userDetailsRequest.getUserId());
        userDetails.setAddress(userDetailsRequest.getAddress());
        userDetails.setPanNo(userDetailsRequest.getPanNo());
        userDetails.setAadhar(userDetailsRequest.getAadhar());
        userDetails.setIncomeSource(userDetailsRequest.getIncomeSource());
        userDetails.setIncomeType(userDetailsRequest.getIncomeType());
        userDetails.setSalary(userDetailsRequest.getSalary());
        userDetails.setCollateral(userDetailsRequest.getCollateral());
        userDetails.setDocuments(new String[0]);

        return userDetailsRepository.save(userDetails);
    }
}
