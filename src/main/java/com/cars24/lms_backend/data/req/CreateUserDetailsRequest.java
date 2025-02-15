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




/*
{
  "_id": {
    "$oid": "67ac9c09e40ca588889101a8"
  },
  "userId": "user12345",
  "address": "123 Main Street, Mumbai, India",
  "panNo": "ABCDE1234F",
  "aadhar": "123456789012",
  "incomeSource": "Job",
  "incomeType": "Salaried",
  "salary": 60000,
  "collateral": "Gold Jewelry"
}


 */