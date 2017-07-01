package com.marbor112.webflux.sample.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by SG0221151 on 7/1/2017.
 */
public class Converter {
    private final ObjectMapper objectMapper = new ObjectMapper();
    public <T> String toJson(T input)
    {
        try {
            return objectMapper.writeValueAsString(input);
        } catch (JsonProcessingException e) {
            throw  new RuntimeException(e);
        }
    }
}
