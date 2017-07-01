package com.marbor112.webflux.sample.domain;

import lombok.Data;

/**
 * Created by Marcin on 6/7/2017.
 */
@Data
public class RepositoryDetails {
    private final String fullName;
    private final String description;
    private final String cloneUrl;
    private final Long stars;
    private String createdAt;

    public RepositoryDetails(String fullName, String description, String cloneUrl, Long stars, String createdAt) {
        this.fullName = fullName;
        this.description = description;
        this.cloneUrl = cloneUrl;
        this.stars = stars;
        this.createdAt = createdAt;
    }
}
