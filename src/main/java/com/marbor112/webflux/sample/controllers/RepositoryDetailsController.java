package com.marbor112.webflux.sample.controllers;

import com.marbor112.webflux.sample.controllers.responses.Response;
import com.marbor112.webflux.sample.services.RepositoryDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static com.marbor112.webflux.sample.controllers.responses.Response.notFound;
import static com.marbor112.webflux.sample.controllers.responses.RestApiMessages.RESOURCE_NOT_FOUND;

@RestController
public class RepositoryDetailsController {
    private final RepositoryDetailsService repositoryDetailsService;

    public RepositoryDetailsController(RepositoryDetailsService repositoryDetailsService) {
        this.repositoryDetailsService = repositoryDetailsService;
    }

    @GetMapping("/repositories/{owner}/{repositoryName}")
    public Mono<ResponseEntity<String>> getRepositoryDetails(@PathVariable String owner, @PathVariable String repositoryName) {
        return repositoryDetailsService.retrieve(owner, repositoryName)
                .map(repositoryDetails -> repositoryDetails
                        .map(Response::ok)
                        .orElse(notFound(RESOURCE_NOT_FOUND.message())));
    }
}
