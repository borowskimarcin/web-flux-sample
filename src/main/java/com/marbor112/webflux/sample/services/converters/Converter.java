package com.marbor112.webflux.sample.services.converters;

public interface Converter<I, R> {
    R convert(I input);
}
