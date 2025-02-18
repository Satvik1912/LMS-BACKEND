package com.cars24.lms_backend.data.entities;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "user_details")
@Data
public class UserDetailsEntity {

    @Id
    private  String udId;  // Auto-generate unique ID

    @NotNull(message = "User ID cannot be null")
    private String userId;

    @NotBlank(message = "Address is required")
    private String address;

    @Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}", message = "Invalid PAN number format")
    private String panNo;

    @Size(min = 12, max = 12, message = "Aadhar number must be 12 digits")
    private String aadhar;

    @NotBlank(message = "Income source is required")
    private String incomeSource;

    @NotBlank(message = "Income type is required")
    private String incomeType;

    @Min(value = 5000, message = "Salary must be at least 5000")
    private int salary;

    private String collateral;

    @NotBlank(message = "Tenure is required in years")
    private int tenure;

    @NotBlank(message = "Amount is required")
    private int principalamount;

    @NotBlank(message = "Interest is required")
    private int interest;

    private String[] documents;
}
