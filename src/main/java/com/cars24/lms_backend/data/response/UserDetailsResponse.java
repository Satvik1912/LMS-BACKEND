package com.cars24.lms_backend.data.response;

import lombok.Data;

@Data
public class UserDetailsResponse {

    private String id;

    private String userId;

    private String address;

    private String panNo;

    private String aadhar;

    private String incomeSource;

    private String incomeType;

    private int salary;

    private String collateral;

    private String[] documents;
}
