package com.cars24.lms_backend.services.impl;

import com.cars24.lms_backend.data.dao.BankDetailsDao;
import com.cars24.lms_backend.data.entities.BankDetailsEntity;
import com.cars24.lms_backend.data.repositories.UserDetailsRepository;
import com.cars24.lms_backend.data.request.BankDetailsRequest;
import com.cars24.lms_backend.data.response.ApiResponse;
import com.cars24.lms_backend.services.BankDetailsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Validated
public class BankDetailsServiceImpl implements BankDetailsService {

    private final BankDetailsDao bankDetailsDao;
    private final UserDetailsRepository userRepository;

    @Override
    public ResponseEntity<ApiResponse> createBankDetails(@Valid BankDetailsRequest request) {

        if (userRepository.existsByUserId(request.getUserId())) {
            try {
                bankDetailsDao.save(request);

                ApiResponse response = new ApiResponse();
                response.setStatusCode(HttpStatus.CREATED.value());
                response.setMessage("Bank details added successfully");
                response.setService("APPUSR-" + HttpStatus.CREATED.value());
                response.setSuccess("TRUE");
                response.setData(null);

                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                "An error occurred while saving bank details: " + e.getMessage(),
                                "APPUSR-" + HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                false, null));
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(HttpStatus.BAD_REQUEST.value(),
                            "User ID does not exist",
                            "APPUSR-" + HttpStatus.BAD_REQUEST.value(),
                            false, null));
        }
    }

    @Override
    public ResponseEntity<ApiResponse> getBankDetails(String userId) {
        if (userRepository.existsByUserId(userId)) {
            try {
                Optional<BankDetailsEntity> bankDetails = bankDetailsDao.get(userId);
                if (bankDetails.isPresent()) {
                    ApiResponse response = new ApiResponse();
                    response.setStatusCode(HttpStatus.OK.value());
                    response.setMessage("Bank details fetched successfully");
                    response.setService("APPUSR-" + HttpStatus.OK.value());
                    response.setSuccess("TRUE");
                    response.setData(bankDetails.get());

                    return ResponseEntity.status(HttpStatus.OK).body(response);
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body(new ApiResponse(HttpStatus.NOT_FOUND.value(),
                                    "No bank details found for this user",
                                    "APPUSR-" + HttpStatus.NOT_FOUND.value(),
                                    false, null));
                }
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                "An error occurred while fetching bank details: " + e.getMessage(),
                                "APPUSR-" + HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                false, null));
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(HttpStatus.BAD_REQUEST.value(),
                            "User ID does not exist",
                            "APPUSR-" + HttpStatus.BAD_REQUEST.value(),
                            false, null));
        }
    }

}

