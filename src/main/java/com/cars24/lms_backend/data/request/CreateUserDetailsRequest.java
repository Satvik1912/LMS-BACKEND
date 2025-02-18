package com.cars24.lms_backend.data.request;


import lombok.Data;

@Data
public class CreateUserDetailsRequest {

    private String userId;
    private String address;
    private String panNo;
    private String aadhar;
    private String incomeSource;
    private String incomeType;
    private int salary;
    private String collateral;
    private double principalAmount;
    private int tenure;
    private double interest;

}
