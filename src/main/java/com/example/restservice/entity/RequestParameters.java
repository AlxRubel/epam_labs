package com.example.restservice.entity;

import java.util.Objects;

public class RequestParameters
{
    private String parameterString;
    private char parameterChar;

    public RequestParameters(String parameterString, char parameterChar) {
        this.parameterString = parameterString;
        this.parameterChar = parameterChar;
    }

    public String getParameterString()
    {
        return parameterString;
    }

    public char getParameterChar()
    {
        return parameterChar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestParameters that = (RequestParameters) o;
        return parameterChar == that.parameterChar && Objects.equals(parameterString, that.parameterString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parameterString, parameterChar);
    }
}
