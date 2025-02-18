package com.cars24.lms_backend.data.dao;

import com.cars24.lms_backend.data.entities.BankDetailsEntity;
import com.cars24.lms_backend.data.request.BankDetailsRequest;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Validated
public interface BankDetailsDao {
    BankDetailsEntity save(@Valid BankDetailsRequest request);
    Optional<BankDetailsEntity> get(String request);

}
