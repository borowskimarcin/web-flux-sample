package com.marbor112.webflux.sample.controllers;

import com.marbor112.webflux.sample.domain.RepositoryDetails;
import com.marbor112.webflux.sample.services.GithubParams;
import com.marbor112.webflux.sample.services.GithubRepositoryDetailsRetriever;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class RepositoryDetailsController {
    private final GithubRepositoryDetailsRetriever githubRepositoryDetailsRetriever;

    public RepositoryDetailsController(GithubRepositoryDetailsRetriever githubRepositoryDetailsRetriever) {
        this.githubRepositoryDetailsRetriever = githubRepositoryDetailsRetriever;
    }

    @GetMapping("v1/repositories/{owner}/{repositoryName}")
    public Mono<ResponseEntity<RepositoryDetails>> getRepositoryDetails(@PathVariable String owner, @PathVariable String repositoryName) {
        return githubRepositoryDetailsRetriever.retrieve(new GithubParams(owner, repositoryName))
                .map(either -> {
                            if (either.isLeft()) {
                                final HttpStatus status = either.getLeft();
                                return ResponseEntity.status(status).build();
                            } else {
                                final RepositoryDetails repositoryDetails = either.swap().getLeft();
                                return ResponseEntity.ok(repositoryDetails);
                            }
                        }
                );
    }
}
