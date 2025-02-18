package com.cars24.lms_backend.data.dao;

import com.cars24.lms_backend.data.entities.LoanStatusEntity;
import jakarta.validation.Valid;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Repository
@Service
@Validated
public interface LoanStatusDao {
    LoanStatusEntity save(@Valid LoanStatusEntity loanStatusEntity);
    List<LoanStatusEntity> findByUserId(String userId);//, it means multiple LoanStatusEntity records can exist for the same userId.
    List<LoanStatusEntity> findAll();
    void deleteById(String id);
}
