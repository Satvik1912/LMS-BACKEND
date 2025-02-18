package com.cars24.lms_backend.data.dao.impl;

import com.cars24.lms_backend.data.dao.LoanStatusDao;
import com.cars24.lms_backend.data.entities.LoanStatusEntity;
import com.cars24.lms_backend.data.repositories.LoanStatusRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Repository
@Validated
public class LoanStatusDaoImpl implements LoanStatusDao {
    @Autowired
    private LoanStatusRepository loanStatusRepository;

    @Override
    public LoanStatusEntity save(@Valid LoanStatusEntity loanStatusEntity) {
        return loanStatusRepository.save(loanStatusEntity);
    }

    public List<LoanStatusEntity> findByUserId(String userId) {
        return loanStatusRepository.findByUserId(userId);
    }

    @Override
    public List<LoanStatusEntity> findAll() {
        return loanStatusRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        loanStatusRepository.deleteById(id);
    }

}
