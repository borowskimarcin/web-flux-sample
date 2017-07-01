package com.marbor112.webflux.sample.controllers;

/**
 * Created by SG0221151 on 7/1/2017.
 */
public enum RestApiMessages {
    RESOURCE_NOT_FOUND("Resource not found"),
    SUCCESS("Success");
    private final String message;

    RestApiMessages(String message)
    {
        this.message = message;
    }

    public String message()
    {
        return message;
    }
}
