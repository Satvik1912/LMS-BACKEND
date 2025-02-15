package com.cars24.lms_backend.data.req;


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

}
