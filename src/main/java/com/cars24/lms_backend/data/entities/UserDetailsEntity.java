package com.cars24.lms_backend.data.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.*;

@Document(collection = "user_details")
@Data
public class UserDetailsEntity {

    @Id
    private String udId;  // Auto-generate unique ID

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

    private String[] documents;

    @Min(value = 50000, message = "Principal amount must be at least 50,000")
    private double principalAmount;

    @Min(value = 1, message = "Tenure must be at least 1 year")
    private int tenure;

    @DecimalMin(value = "0.1", message = "Interest rate must be greater than 0%")
    @DecimalMax(value = "100.0", message = "Interest rate cannot exceed 100%")
    private double interest;
}
