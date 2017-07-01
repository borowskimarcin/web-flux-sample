package com.marbor112.webflux.sample.controllers;

import com.marbor112.webflux.sample.services.RepositoryDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class RepositoryDetailsController {
    @Autowired
    private RepositoryDetailsService repositoryDetailsService;

    @GetMapping("/repositories/{owner}/{repositoryName}")
    public Mono<ResponseEntity<RepositoryDetailsResponse>> getRepositoryDetails(
            @PathVariable String owner, @PathVariable String repositoryName) {
        return repositoryDetailsService.retrieve(owner, repositoryName)
                .map(repositoryDetails -> repositoryDetails
                        .map(result -> new RepositoryDetailsResponse(HttpStatus.OK, RestApiMessages.SUCCESS.message(), repositoryDetails)
                                .toResponseEntity())
                        .orElse(new RepositoryDetailsResponse(HttpStatus.NOT_FOUND, RestApiMessages.RESOURCE_NOT_FOUND.message(), repositoryDetails)
                                .toResponseEntity()));
    }
}
