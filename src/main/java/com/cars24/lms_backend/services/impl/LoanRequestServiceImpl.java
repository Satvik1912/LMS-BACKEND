package com.cars24.lms_backend.services.impl;


import com.cars24.lms_backend.data.dao.impl.LoanRequestDaoImpl;
import com.cars24.lms_backend.data.entities.LoanRequestEntity;
import com.cars24.lms_backend.data.entities.UserDetailsEntity;
import com.cars24.lms_backend.data.request.CreateUserDetailsRequest;
import com.cars24.lms_backend.data.request.LoanRequestEntityRequest;
import com.cars24.lms_backend.data.response.ApiResponse;
import com.cars24.lms_backend.services.LoanRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class LoanRequestServiceImpl implements LoanRequestService {

    private final LoanRequestDaoImpl loanRequestDao;

    public ApiResponse createLoanRequestService(LoanRequestEntityRequest loanRequestEntityRequest){

        ApiResponse createLoanRequestServiceResponse=new ApiResponse();

        //call to dao
        LoanRequestEntity loanRequestCreated =loanRequestDao.createLoanRequestDao(loanRequestEntityRequest);

        createLoanRequestServiceResponse.setStatusCode(HttpStatus.OK.value());
        createLoanRequestServiceResponse.setSuccess(true);
        createLoanRequestServiceResponse.setMessage("Loan request inserted successfully");
        createLoanRequestServiceResponse.setService("APPLOANREQ"+ HttpStatus.OK.value());
        createLoanRequestServiceResponse.setData(loanRequestCreated);

        return createLoanRequestServiceResponse;
    }
}
