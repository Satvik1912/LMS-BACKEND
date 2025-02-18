package com.cars24.lms_backend.data.dao.impl;

import com.cars24.lms_backend.data.dao.LoanDetailsDao;
import com.cars24.lms_backend.data.enitities.LoanDetailsEntity;
import com.cars24.lms_backend.data.enums.LoanType;
import com.cars24.lms_backend.data.repositories.LoanDetailsRepository;
import com.cars24.lms_backend.data.request.CreateLoanDetailsRequest;
import com.cars24.lms_backend.data.response.GetLoanDetailsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoanDetailsDaoImpl implements LoanDetailsDao {

    private final LoanDetailsRepository loanDetailsRepository;

    @Override

    public GetLoanDetailsResponse getLoanDetails(String loanDetailsId) {
        LoanDetailsEntity loanDetailsEntity= loanDetailsRepository.findById(loanDetailsId) .orElseThrow(()-> new RuntimeException("Loan id not found"));
        GetLoanDetailsResponse getLoanDetailsResponse=new GetLoanDetailsResponse();
        getLoanDetailsResponse.setLoanType(loanDetailsEntity.getLoanType());
        getLoanDetailsResponse.setRateOfInterest(loanDetailsEntity.getRateOfInterest());
        log.info("[in loan details dao]:{}",getLoanDetailsResponse);
        return  getLoanDetailsResponse;

    }

    @Override
    public int createLoanDetails(CreateLoanDetailsRequest createLoanDetailsRequest) {
        LoanDetailsEntity loanDetailsEntity=new LoanDetailsEntity();

        loanDetailsEntity.setImageUrl(createLoanDetailsRequest.getImageUrl());
        loanDetailsEntity.setLoanType(LoanType.valueOf(String.valueOf(createLoanDetailsRequest.getLoanType())));
        loanDetailsEntity.setRateOfInterest(createLoanDetailsRequest.getRateOfInterest());

        loanDetailsRepository.save(loanDetailsEntity);
        log.info("[in create loan details dao]:{}",createLoanDetailsRequest);
        return 0;
    }
}
