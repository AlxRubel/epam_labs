package com.example.restservice.entity;

import com.example.restservice.entity.RequestParameters;

import java.util.List;

public class WrapperObject
{
    private List<RequestParameters> objects;

    public List<RequestParameters> getObjects()
    {
        return objects;
    }

    public void setObjects(List<RequestParameters> objects)
    {
        this.objects = objects;
    }
}
