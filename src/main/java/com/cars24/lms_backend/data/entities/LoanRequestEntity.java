package com.cars24.lms_backend.data.entities;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "loan-request")
@Data
public class LoanRequestEntity {

    @Id
    private String lrId;

    private String userId;

    private String collateral;

    private int tenure;

    private int principalAmount;


    private double interest;
}
