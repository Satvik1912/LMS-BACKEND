package com.cars24.lms_backend.data.res;


import lombok.Data;

@Data
public class ApiResponse {

    private int statusCode;

    private boolean success;

    private String message;

    private Object data;

    private String service;


}
