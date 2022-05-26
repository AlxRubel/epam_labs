package com.example.restservice.service;

import com.example.restservice.entity.Inclusion;
import com.example.restservice.entity.RequestParameters;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterInclusionService {

    public Inclusion calculateCharacterInclusion(RequestParameters requestParameters)
    {
        String name = requestParameters.getParameterString();
        char symbol = requestParameters.getParameterChar();
        try {
            int counter = 0;
            for (int i = 0; i < name.length(); i++) {
                if (name.charAt(i) == symbol) {
                    counter++;
                }
            }
            return new Inclusion(counter);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public int calculateMaxResult(List<Inclusion> list) {
        int max = 0;
        if (!list.isEmpty())
            max = list.stream().map(Inclusion::getCounter).mapToInt(Integer::intValue).max().getAsInt();
        return max;
    }

    public int calculateMinResult(List<Inclusion> list) {
        int min = 0;
        if (!list.isEmpty())
            min = list.stream().map(Inclusion::getCounter).mapToInt(Integer::intValue).min().getAsInt();
        return min;
    }

    public int calculateSumResult(List<Inclusion> list) {
        int sum = 0;
        if (!list.isEmpty()) sum = list.stream().map(Inclusion::getCounter).mapToInt(Integer::intValue).sum();
        return sum;
    }
}
