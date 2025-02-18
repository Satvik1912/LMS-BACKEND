package com.cars24.lms_backend.data.request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BankDetailsRequest {

        @NotNull(message = "User ID cannot be null")
        private String userId;

        @NotNull(message = "Account holder name cannot be null")
        @Size(min = 2, message = "Account holder name must be at least 2 characters long")
        private String accountHolderName;

        @NotNull(message = "Account number cannot be null")
        @Pattern(regexp = "\\d{11,}", message = "Account number must be at least 11 digits long")
        private String accountNo;

        @NotNull(message = "IFSC code cannot be null")
        @Pattern(regexp = "^[A-Za-z0-9]{1,12}$", message = "IFSC code must be alphanumeric and up to 12 characters")
        private String ifsc_code;

        @NotNull(message = "Bank name cannot be null")
        @Size(min = 2, message = "Bank name must be at least 2 characters long")
        private String bankName;
    }


