package com.marbor112.webflux.sample.services;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GithubParams {
    private final String owner;
    private final String repositoryName;
}
