package com.cars24.lms_backend.data.enitities;

import com.cars24.lms_backend.data.enums.LoanType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection="loanDetails")

public class LoanDetailsEntity {
    @Id
    private String id;

    private double rateOfInterest;

    private LoanType loanType;

    private String imageUrl;


}
