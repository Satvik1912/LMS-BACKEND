package com.cars24.lms_backend.services.impl;

import com.cars24.lms_backend.data.dao.LoanStatusDao;
import com.cars24.lms_backend.data.entities.LoanStatusEntity;
import com.cars24.lms_backend.data.entities.UserDetailsEntity;
import com.cars24.lms_backend.data.enums.LoanStatus;
import com.cars24.lms_backend.data.repositories.LoanStatusRepository;
import com.cars24.lms_backend.data.repositories.UserDetailsRepository;
import com.cars24.lms_backend.data.request.LoanStatusRequest;
import com.cars24.lms_backend.data.response.ApiResponse;
import com.cars24.lms_backend.data.response.LoanStatusResponse;
import com.cars24.lms_backend.services.LoanStatusService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Validated
public class LoanStatusServiceImpl implements LoanStatusService {

    @Autowired
    private LoanStatusDao loanStatusDao;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private LoanStatusRepository loanStatusRepository;

    @Override
    public ResponseEntity<ApiResponse> createLoanStatus(@Valid LoanStatusRequest request) {
        try {
            // Fetch user details using udId from UserDetailsRepository
            UserDetailsEntity user = userDetailsRepository.findByUdId(request.getUdId())
                    .orElseThrow(() -> new RuntimeException("User not found with udId: " + request.getUdId()));

            // Calculate loan amount
            double principalAmount = user.getPrincipalamount();
            double loanAmount = principalAmount + (principalAmount * user.getInterest() * user.getTenure()) / 100;

            // Create and save LoanStatusEntity
            LoanStatusEntity loanStatusEntity = new LoanStatusEntity();
            loanStatusEntity.setUserId(user.getUserId());
            loanStatusEntity.setUdId(user.getUdId());
            loanStatusEntity.setLoanAmount(loanAmount);
            loanStatusEntity.setLoanStatus(LoanStatus.pending);

            LoanStatusEntity savedLoanStatus = loanStatusDao.save(loanStatusEntity);

            // Create response data
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("userId", savedLoanStatus.getUserId());
            responseData.put("udId", savedLoanStatus.getUdId());
            responseData.put("loanAmount", savedLoanStatus.getLoanAmount());
            responseData.put("loanStatus", savedLoanStatus.getLoanStatus());

            // Return success response
            ApiResponse response = new ApiResponse(
                    HttpStatus.CREATED.value(),
                    "Loan status created successfully",
                    "APPUSR-" + HttpStatus.CREATED.value(),
                    true, responseData.toString()
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(HttpStatus.BAD_REQUEST.value(),
                            e.getMessage(),
                            "APPUSR-" + HttpStatus.BAD_REQUEST.value(),
                            false, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                            "An error occurred: " + e.getMessage(),
                            "APPUSR-" + HttpStatus.INTERNAL_SERVER_ERROR.value(),
                            false, null));
        }
    }


    @Override
    public List<LoanStatusResponse> getLoanStatusByUserId(String userId) {
        // Fetch LoanStatusEntity list for the given userId
        List<LoanStatusEntity> loanStatusEntities = loanStatusRepository.findByUserId(userId);

        // If no records are found, return an empty list
        if (loanStatusEntities == null || loanStatusEntities.isEmpty()) {
            return Collections.emptyList();
        }

        // Convert entity list to response list
        return loanStatusEntities.stream().map(entity -> {
            LoanStatusResponse response = new LoanStatusResponse();
            response.setId(entity.getId());
            response.setUserId(entity.getUserId());
            response.setUdId(entity.getUdId());
            response.setLoanAmount((int) entity.getLoanAmount());
            response.setLoanStatus(entity.getLoanStatus());
            return response;
        }).collect(Collectors.toList());
    }



    @Override
    public ResponseEntity<ApiResponse> updateLoanStatus(String udId, LoanStatusRequest request) {
        // Check if udId exists
        if (!loanStatusRepository.existsByUdId(udId)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(HttpStatus.BAD_REQUEST.value(),
                            "User detail id does not exist",
                            "APPUSR-" + HttpStatus.BAD_REQUEST.value(),
                            false, null));
        }

        try {
            Optional<LoanStatusEntity> loanStatusEntity = loanStatusRepository.findByUdId(udId);

            if (loanStatusEntity.isPresent()) {
                LoanStatusEntity entity = loanStatusEntity.get();
                entity.setLoanStatus(request.getLoanStatus());
                loanStatusRepository.save(entity);

                ApiResponse response = new ApiResponse(
                        HttpStatus.OK.value(),
                        "Status Updated Successfully",
                        "APPUSR-" + HttpStatus.OK.value(),
                        true, null);

                return ResponseEntity.ok(response);
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(HttpStatus.NOT_FOUND.value(),
                            "Loan status record not found",
                            "APPUSR-" + HttpStatus.NOT_FOUND.value(),
                            false, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                            "An error occurred while updating: " + e.getMessage(),
                            "APPUSR-" + HttpStatus.INTERNAL_SERVER_ERROR.value(),
                            false, null));
        }
    }


    @Override
    public void deleteLoanStatus(String id) {
        loanStatusDao.deleteById(id);
    }
}
