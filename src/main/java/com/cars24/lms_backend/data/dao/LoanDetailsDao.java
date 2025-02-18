package com.cars24.lms_backend.data.dao;

import com.cars24.lms_backend.data.request.CreateLoanDetailsRequest;
import com.cars24.lms_backend.data.response.GetLoanDetailsResponse;

public interface LoanDetailsDao {
    GetLoanDetailsResponse getLoanDetails(String loanDetailsId);
    int createLoanDetails(CreateLoanDetailsRequest createLoanDetailsRequest);
}
