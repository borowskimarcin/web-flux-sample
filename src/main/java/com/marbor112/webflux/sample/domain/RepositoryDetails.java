package com.marbor112.webflux.sample.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Marcin on 6/7/2017.
 */
@Data
@AllArgsConstructor
public class RepositoryDetails {
    private final String fullName;
    private final String description;
    private final String cloneUrl;
    private final Long stars;
    private final String createdAt;
}
