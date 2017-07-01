package com.marbor112.webflux.sample.controllers;

import com.marbor112.webflux.sample.domain.RepositoryDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

/**
 * Created by SG0221151 on 7/1/2017.
 */
public class RepositoryDetailsResponse {
    private final HttpStatus status;
    private final String message;
    private final RepositoryDetails repositoryDetails;

    RepositoryDetailsResponse(HttpStatus status, String message, Optional<RepositoryDetails> repositoryDetails) {
        this.status = status;
        this.message = message;
        this.repositoryDetails = repositoryDetails.orElse(null);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public RepositoryDetails getRepositoryDetails() {
        return repositoryDetails;
    }

    ResponseEntity<RepositoryDetailsResponse> toResponseEntity()
    {
        return new ResponseEntity <>(
               this,
                getStatus());
    }
}
