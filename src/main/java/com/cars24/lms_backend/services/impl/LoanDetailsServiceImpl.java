package com.cars24.lms_backend.services.impl;

import com.cars24.lms_backend.data.dao.impl.LoanDetailsDaoImpl;
import com.cars24.lms_backend.data.request.CreateLoanDetailsRequest;
import com.cars24.lms_backend.data.response.ApiResponse;
import com.cars24.lms_backend.data.response.GetLoanDetailsResponse;
import com.cars24.lms_backend.services.LoanDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoanDetailsServiceImpl implements LoanDetailsService {
    private final LoanDetailsDaoImpl loanDetailsDao;

    @Override
    public ResponseEntity<ApiResponse> getLoanDetails(String loanDetailsId) {
        ApiResponse apiResponse=new ApiResponse();

        GetLoanDetailsResponse getLoanDetailsResponse=loanDetailsDao.getLoanDetails(loanDetailsId);

        Map<String,Object> messageResponse=new HashMap<>();
        messageResponse.put("Loan Type",getLoanDetailsResponse.getLoanType());
        messageResponse.put("Rate of interest",String.valueOf(getLoanDetailsResponse.getRateOfInterest()));

        apiResponse.setStatusCode(HttpStatus.OK.value());
        apiResponse.setService("Get customer - "+HttpStatus.OK.value());
        apiResponse.setMessage("Get user successful");
        apiResponse.setData(messageResponse);
        apiResponse.setSuccess(true);
        log.info("[in loan details service]:{}",messageResponse);
        return ResponseEntity.ok().body(apiResponse);

    }

    @Override
    public ResponseEntity<ApiResponse> createLoanDetails(CreateLoanDetailsRequest createLoanDetailsRequest) {
       ApiResponse apiResponse=new ApiResponse();
       loanDetailsDao.createLoanDetails(createLoanDetailsRequest);
       apiResponse.setMessage("Loan Details created successfully");
       apiResponse.setData(null);
       apiResponse.setService("Loan Details - "+HttpStatus.OK.value());
       apiResponse.setStatusCode(HttpStatus.OK.value());
       apiResponse.setSuccess(true);
       log.info("[in create loan details service]:{}",apiResponse);
       return ResponseEntity.ok().body(apiResponse);
    }
}
