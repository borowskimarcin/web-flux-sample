package com.marbor112.webflux.sample.services.converters;

import com.marbor112.webflux.sample.domain.RepositoryDetails;
import com.marbor112.webflux.sample.services.dto.GithubRepositoryDetails;

public class ToRepositoryDetailsConverter implements Converter<GithubRepositoryDetails, RepositoryDetails> {
    @Override
    public RepositoryDetails convert(GithubRepositoryDetails input) {
        return new RepositoryDetails(
                input.getFullName(),
                input.getDescription(),
                input.getCloneUrl(),
                input.getStars(),
                input.getCreatedAt()
        );
    }
}
