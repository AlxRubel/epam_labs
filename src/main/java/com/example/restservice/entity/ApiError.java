package com.example.restservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiError {

    @JsonProperty
    private String errorMessage;

    @JsonProperty
    private String debugMessage;

    public ApiError(String string1, String string2) {
        this.errorMessage = string1;
        this.debugMessage = string2;
    }

}
