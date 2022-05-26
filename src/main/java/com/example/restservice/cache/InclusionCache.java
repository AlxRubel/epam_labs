package com.example.restservice.cache;

import com.example.restservice.entity.Inclusion;
import com.example.restservice.entity.RequestParameters;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedHashMap;

@Component
public class InclusionCache {
    private final HashMap<RequestParameters, Inclusion> cache = new LinkedHashMap<>();

    public boolean contains(RequestParameters key) {
        return cache.containsKey(key);
    }

    public void addCache(RequestParameters key, Inclusion result) {
        cache.put(key, result);
    }

    public Inclusion getResult(RequestParameters key) {
        return cache.get(key);
    }

    public HashMap<RequestParameters, Inclusion> getCache() {
        return cache;
    }
}
