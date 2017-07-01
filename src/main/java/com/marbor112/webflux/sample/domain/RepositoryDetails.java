package com.marbor112.webflux.sample.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Created by Marcin on 6/7/2017.
 */

@JsonDeserialize(using = RepositoryDetailsDeserializer.class)
public class RepositoryDetails {
    private final String fullName;
    private final String description;
    private final String cloneUrl;
    private final Long stars;
    private String createdAt;

    RepositoryDetails(String fullName, String description, String cloneUrl, Long stars, String createdAt) {
        this.fullName = fullName;
        this.description = description;
        this.cloneUrl = cloneUrl;
        this.stars = stars;
        this.createdAt = createdAt;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDescription() {
        return description;
    }

    public String getCloneUrl() {
        return cloneUrl;
    }

    public Long getStars() {
        return stars;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public boolean exists()
    {
        return getCloneUrl() != null
                || getCreatedAt() != null
                || getDescription() != null
                || getFullName() != null
                || getStars() != null;
    }
}
