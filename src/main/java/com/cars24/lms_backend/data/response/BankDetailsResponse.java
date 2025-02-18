package com.cars24.lms_backend.data.response;


import lombok.Data;

@Data
public class BankDetailsResponse {
   private String bId;
    private String userId;
    private String accountHolderName;
    private String accountNo;
    private String ifsc_code;
    private String bankName;
}
