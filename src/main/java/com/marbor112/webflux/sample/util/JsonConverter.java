package com.marbor112.webflux.sample.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by SG0221151 on 7/1/2017.
 */
public class JsonConverter implements Converter {
    private final ObjectMapper objectMapper = new ObjectMapper();
    public String convert(Object input)
    {
        try {
            return objectMapper.writeValueAsString(input);
        } catch (JsonProcessingException e) {
            throw  new RuntimeException(e);
        }
    }
}
