package com.cars24.lms_backend.data.dao.impl;

import com.cars24.lms_backend.data.dao.LoanRequestDao;
import com.cars24.lms_backend.data.entities.LoanRequestEntity;
import com.cars24.lms_backend.data.entities.UserDetailsEntity;
import com.cars24.lms_backend.data.entities.UsersEntity;
import com.cars24.lms_backend.data.repositories.LoanRequestRepository;
import com.cars24.lms_backend.data.repositories.UserDetailsRepository;
import com.cars24.lms_backend.data.repositories.UserRepository;
import com.cars24.lms_backend.data.request.CreateUserDetailsRequest;
import com.cars24.lms_backend.data.request.LoanRequestEntityRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class LoanRequestDaoImpl implements LoanRequestDao {

    private final LoanRequestRepository loanRequestRepository;
    private final UserRepository userRepo;

    public LoanRequestEntity createLoanRequestDao(LoanRequestEntityRequest loanRequestEntityRequest) {

        // I have to check if userId exists in user collection
        //also we have to ensure that we don't insert multiple documents of same user (to be implemented later)

        Optional<UsersEntity> user = userRepo.findById(loanRequestEntityRequest.getUserId());

        if(user.isPresent()){

            ObjectMapper objectMapper =new ObjectMapper();
            LoanRequestEntity loanRequest = objectMapper.convertValue(loanRequestEntityRequest,LoanRequestEntity.class);


            return loanRequestRepository.save(loanRequest);
        }else{
            throw new RuntimeException("User doesn't exists");
        }
    }
}
