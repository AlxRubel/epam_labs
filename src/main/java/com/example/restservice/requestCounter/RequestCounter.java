package com.example.restservice.requestCounter;

public class RequestCounter
{
    private static int numberOfRequests;

    public static int increase()
    {
        return ++numberOfRequests;
    }

    public static Integer getNumberOfRequests()
    {
        return numberOfRequests;
    }

}
