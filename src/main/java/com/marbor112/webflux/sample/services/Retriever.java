package com.marbor112.webflux.sample.services;

public interface Retriever <I, R> {
    R retrieve(I input);
}
