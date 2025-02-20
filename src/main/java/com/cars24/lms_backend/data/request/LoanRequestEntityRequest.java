package com.cars24.lms_backend.data.request;


import lombok.Data;

@Data
public class LoanRequestEntityRequest {

    private String userId;

    private String collateral;

    private int tenure;

    private int principalAmount;

    private double interest;
}
