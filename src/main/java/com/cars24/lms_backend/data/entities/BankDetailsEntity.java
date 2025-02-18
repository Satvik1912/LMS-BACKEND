package com.cars24.lms_backend.data.entities;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "bank_details")
@Data
public class BankDetailsEntity {

    @Id
    private String bId;

    @NotNull(message = "User ID cannot be null")
    private String userId;

    @NotNull(message = "Account holder name cannot be null")
    @Size(min = 2, message = "Account holder name must be at least 2 characters long")
    private String accountHolderName;

    @NotNull(message = "Account number cannot be null")
    @Pattern(regexp = "\\d{11,}", message = "Account number must be at least 11 digits long") // Ensures only numbers with at least 11 digits
    @Field("accountNo")
    private String accountNo;

    @NotNull(message = "IFSC code cannot be null")
    @Pattern(regexp = "^[A-Za-z0-9]{1,12}$", message = "IFSC code must be alphanumeric and up to 12 characters")
    @Field("IFSC_code")
    private String ifsc_code;

    @NotNull(message = "Bank name cannot be null")
    @Size(min = 2, message = "Bank name must be at least 2 characters long")
    @Field("bankName")
    private String bankName;
}
