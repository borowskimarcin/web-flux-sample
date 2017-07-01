package com.marbor112.webflux.sample.services;

import com.marbor112.webflux.sample.domain.MaybeRepositoryDetails;
import com.marbor112.webflux.sample.domain.RepositoryDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
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
    private final String repositoryDetailsGithubApiUrl;
    private final WebClient webClient = WebClient.create();

    public RepositoryDetailsService(@Value("${api.url.github.repository.details}") String repositoryDetailsGithubApiUrl) {
        this.repositoryDetailsGithubApiUrl = repositoryDetailsGithubApiUrl;
    }

    @Cacheable("repositories")
    public Mono<Optional<RepositoryDetails>> retrieve(String owner, String repositoryName) {
        return webClient
                .get()
                .uri(repositoryDetailsGithubApiUrl + "/" + owner + "/" + repositoryName)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .flatMap(response -> response.bodyToMono(MaybeRepositoryDetails.class))
                .map(this::toRepositoryDetails);


    }

    private Optional<RepositoryDetails> toRepositoryDetails(MaybeRepositoryDetails maybe) {
        if (maybe.exists()) {
            return Optional.of(new RepositoryDetails(
                    maybe.getFullName(),
                    maybe.getDescription(),
                    maybe.getCloneUrl(),
                    maybe.getStars(),
                    maybe.getCreatedAt()));
        }
        else
        {
            return Optional.empty();
        }
    }
}
