package com.marbor112.webflux.sample.controllers;

import com.marbor112.webflux.sample.domain.RepositoryDetails;
import com.marbor112.webflux.sample.services.RepositoryDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class RepositoryDetailsController {
    private final RepositoryDetailsService repositoryDetailsService;

    public RepositoryDetailsController(RepositoryDetailsService repositoryDetailsService) {
        this.repositoryDetailsService = repositoryDetailsService;
    }

    @GetMapping("/repositories/{owner}/{repositoryName}")
    public Mono<ResponseEntity<RepositoryDetails>> getRepositoryDetails(@PathVariable String owner, @PathVariable String repositoryName) {
        return repositoryDetailsService.retrieve(owner, repositoryName)
                .map(either -> {
                            if (either.isLeft()) {
                                HttpStatus status = either.getLeft();
                                return ResponseEntity.status(status).build();
                            } else {
                                RepositoryDetails repositoryDetails = either.swap().getLeft();
                                return ResponseEntity.ok(repositoryDetails);
                            }
                        }
                );
    }
}
