package com.example.restservice.entity;

import java.util.List;

public class Dto
{
    private List<Inclusion> numberOfInclusionsList;
    private int maxResult;
    private int minResult;
    private int sumResult;

    public Dto(List<Inclusion>a,int b,int c,int d)
    {
        numberOfInclusionsList=a;
        maxResult=b;
        minResult=c;
        sumResult=d;
    }

    public int getMaxResult() {
        return maxResult;
    }

    public int getMinResult() {
        return minResult;
    }

    public List<Inclusion> getNumberOfInclusionsList() {
        return numberOfInclusionsList;
    }

    public void setNumberOfInclusionsList(List<Inclusion> numberOfInclusionsList) {
        this.numberOfInclusionsList = numberOfInclusionsList;
    }

    public int getSumResult() {
        return sumResult;
    }
}
