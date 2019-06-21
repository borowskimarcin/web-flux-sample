package com.marbor112.webflux.sample.services;

import com.marbor112.webflux.sample.domain.RepositoryDetails;
import com.marbor112.webflux.sample.services.converters.ToRepositoryDetailsConverter;
import com.marbor112.webflux.sample.services.dto.GithubRepositoryDetails;
import io.vavr.control.Either;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Created by Marcin on 6/7/2017.
 */
@Service
public class GithubRepositoryDetailsRetriever implements Retriever<GithubParams, Mono<Either<HttpStatus, RepositoryDetails>>> {
    private static final String PATH_SEPARATOR = "/";
    private final String repositoryDetailsGithubApiUrl;
    private final WebClient webClient;
    private final ToRepositoryDetailsConverter converter;

    @Autowired
    public GithubRepositoryDetailsRetriever(@Value("${api.url.github.repository.details}") String repositoryDetailsGithubApiUrl) {
        this(repositoryDetailsGithubApiUrl, WebClient.create(), new ToRepositoryDetailsConverter());
    }

    public GithubRepositoryDetailsRetriever(
            @Value("${api.url.github.repository.details}") String repositoryDetailsGithubApiUrl,
            WebClient webClient,
            ToRepositoryDetailsConverter converter) {
        this.repositoryDetailsGithubApiUrl = repositoryDetailsGithubApiUrl;
        this.webClient = webClient;
        this.converter = converter;
    }

    @Cacheable("repositories")
    public Mono<Either<HttpStatus, RepositoryDetails>> retrieve(GithubParams githubParams) {
        return webClient
                .get()
                .uri(repositoryDetailsGithubApiUrl + PATH_SEPARATOR + githubParams.getOwner() + PATH_SEPARATOR + githubParams.getRepositoryName())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .flatMap(this::handleResponse);
    }

    private Mono<Either<HttpStatus, RepositoryDetails>> handleResponse(ClientResponse clientResponse) {
        if (!clientResponse.statusCode().is2xxSuccessful()) {
            return Mono.just(Either.left(clientResponse.statusCode()));
        } else {
            return clientResponse.bodyToMono(GithubRepositoryDetails.class)
                    .map(this.converter::convert)
                    .map(Either::right);
        }
    }
}
