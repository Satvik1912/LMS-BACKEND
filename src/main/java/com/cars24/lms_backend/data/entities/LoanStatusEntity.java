package com.cars24.lms_backend.data.entities;

import com.cars24.lms_backend.data.enums.LoanStatus;
import com.fasterxml.jackson.databind.annotation.EnumNaming;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Generated;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import lombok.Data;


@Document(collection = "loan_status")
@Data
public class LoanStatusEntity {

    @Id
    private String id; // MongoDB will generate the unique ID automatically
    @Field("udId")
    private String udId;
    @Field("userId")
    private String userId;

    @Field("loanAmount")
    private double loanAmount;

    @Field("loanStatus")
    private LoanStatus loanStatus;
}
