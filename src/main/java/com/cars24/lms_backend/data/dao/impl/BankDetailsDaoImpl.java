package com.cars24.lms_backend.data.dao.impl;

import com.cars24.lms_backend.data.dao.BankDetailsDao;
import com.cars24.lms_backend.data.entities.BankDetailsEntity;
import com.cars24.lms_backend.data.repositories.BankDetailsRepository;
import com.cars24.lms_backend.data.request.BankDetailsRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Validated
public class BankDetailsDaoImpl implements BankDetailsDao {

    private final BankDetailsRepository bankDetailsRepository;

    @Override
    public BankDetailsEntity save(@Valid BankDetailsRequest request) {
        BankDetailsEntity bankDetailsEntity = new BankDetailsEntity();
        bankDetailsEntity.setUserId(request.getUserId());
        bankDetailsEntity.setAccountHolderName(request.getAccountHolderName());
        bankDetailsEntity.setAccountNo(request.getAccountNo());
        bankDetailsEntity.setIfsc_code(request.getIfsc_code());
        bankDetailsEntity.setBankName(request.getBankName());

        return bankDetailsRepository.save(bankDetailsEntity);
    }
    @Override
    public Optional<BankDetailsEntity> get(String userId) {
            return bankDetailsRepository.findByUserId(userId);
        }

    }

