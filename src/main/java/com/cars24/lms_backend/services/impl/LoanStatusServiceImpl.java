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
    public ResponseEntity<ApiResponse> createLoanStatus(@Valid String udId) {

        try {
            // Fetch user details using udId from UserDetailsRepository
            Optional<UserDetailsEntity> userOptional = userDetailsRepository.findByUdId(udId);

            if (!userOptional.isPresent()) {
                throw new RuntimeException("User does not exist");
            }

            UserDetailsEntity user = userOptional.get();

            // Ensure the necessary data is present before calculating loan amount
            if (user.getPrincipalAmount() == 0 || user.getInterest() == 0 || user.getTenure() == 0) {
                throw new RuntimeException("Required data for loan calculation is missing");
            }

            // Calculate the loan amount (principal + interest over tenure)
            double principalAmount = user.getPrincipalAmount();
            double interestRate = user.getInterest();
            double tenure = user.getTenure();
            double loanAmount = principalAmount + (principalAmount * interestRate * tenure) / 100;

            System.out.println("Checking ----> Principal: " + principalAmount + ", Loan Amount: " + loanAmount);


            Optional<LoanStatusEntity> existingLoanStatus = loanStatusRepository.findByUdId(udId);
            if (existingLoanStatus.isPresent()) {
                throw new RuntimeException("Loan status already exists for this user");
            }

            // Create and save LoanStatusEntity
            LoanStatusEntity loanStatusEntity = new LoanStatusEntity();
            loanStatusEntity.setUserId(user.getUserId());
            loanStatusEntity.setUdId(udId);
            loanStatusEntity.setLoanAmount(loanAmount);
            loanStatusEntity.setLoanStatus(LoanStatus.pending);

            LoanStatusEntity savedLoanStatus = loanStatusDao.save(loanStatusEntity);

            Map<String, Object> responseData = new HashMap<>();
            responseData.put("userId", savedLoanStatus.getUserId());
            responseData.put("udId", udId);
            responseData.put("loanAmount", savedLoanStatus.getLoanAmount());
            responseData.put("loanStatus", savedLoanStatus.getLoanStatus());

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
