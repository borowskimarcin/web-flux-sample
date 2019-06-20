package com.marbor112.webflux.sample.services.dto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.boot.jackson.JsonObjectDeserializer;

import java.io.IOException;

/**
 * Created by Marcin on 6/7/2017.
 */
public class RepositoryDetailsDeserializer extends JsonObjectDeserializer<GithubRepositoryDetails> {
    @Override
    protected GithubRepositoryDetails deserializeObject(JsonParser jsonParser, DeserializationContext deserializationContext, ObjectCodec objectCodec, JsonNode jsonNode) throws IOException {
        String fullName = nullSafeValue(jsonNode.get("full_name"), String.class);
        String description = nullSafeValue(jsonNode.get("description"), String.class);
        String cloneUrl = nullSafeValue(jsonNode.get("clone_url"), String.class);
        Long stars = nullSafeValue(jsonNode.get("stargazers_count"), Long.class);
        String createdAt = nullSafeValue(jsonNode.get("created_at"), String.class);

        return new GithubRepositoryDetails();
    }
}
