package com.cars24.lms_backend.data.enitities;

import com.cars24.lms_backend.data.enums.TransactionType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data

@Document(collection = "transactions")
public class TransactionEntity {

    @Id
    private String id;

    private double amount;

    private TransactionType transactionType;

    @DBRef
    private UserDetailsEntity userDetails;






}
