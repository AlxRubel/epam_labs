package com.example.restservice.controller;

import com.example.restservice.requestCounter.RequestCounter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestCounterController
{
    @GetMapping(value = "/requestCounter")
    public int getNumberOfRequests()
    {
        return RequestCounter.getNumberOfRequests();
    }

}
