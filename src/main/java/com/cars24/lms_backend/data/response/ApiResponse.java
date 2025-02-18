package com.cars24.lms_backend.data.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
    private int statusCode;
    private String message;
    private String success;
    private Object data;
    private String service;
}
