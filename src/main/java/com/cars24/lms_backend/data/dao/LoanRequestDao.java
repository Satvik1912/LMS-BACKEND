package com.cars24.lms_backend.data.dao;

import com.cars24.lms_backend.data.entities.LoanRequestEntity;
import com.cars24.lms_backend.data.request.LoanRequestEntityRequest;

public interface LoanRequestDao {
    LoanRequestEntity createLoanRequestDao(LoanRequestEntityRequest loanRequestEntityRequest);
}
