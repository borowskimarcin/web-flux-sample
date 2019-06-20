package com.marbor112.webflux.sample.services.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Created by Marcin on 6/7/2017.
 */

@Data
@NoArgsConstructor
@JsonIgnoreProperties
public class GithubRepositoryDetails {
    @JsonProperty("full_name")
    private String fullName;
    private String description;
    @JsonProperty("clone_url")
    private String cloneUrl;
    @JsonProperty("stargazers_count")
    private Long stars;
    @JsonProperty("created_at")
    private String createdAt;

}
