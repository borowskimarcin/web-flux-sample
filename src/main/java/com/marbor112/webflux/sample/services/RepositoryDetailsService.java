package com.marbor112.webflux.sample.services;

import com.marbor112.webflux.sample.domain.RepositoryDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * Created by Marcin on 6/7/2017.
 */
@Service
public class RepositoryDetailsService {

    @Value("${api.url.github.repository.details}")
    private String repositoryDetailsGithubApiUrl;

    private final WebClient webClient = WebClient.create();

    public Mono<Optional<RepositoryDetails>> retrieve(String owner, String repositoryName) {
        return webClient
                .get()
                .uri(repositoryDetailsGithubApiUrl + "/" + owner + "/" + repositoryName)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .flatMap(response -> response.bodyToMono(RepositoryDetails.class))
                .map(this::toOptional);


    }

    private Optional<RepositoryDetails> toOptional(RepositoryDetails repositoryDetails) {
        if (repositoryDetails.exists()) {
            return Optional.of(repositoryDetails);
        }
        else
        {
            return Optional.empty();
        }
    }
}
