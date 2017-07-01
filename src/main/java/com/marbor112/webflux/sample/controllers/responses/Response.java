package com.marbor112.webflux.sample.controllers.responses;

import com.marbor112.webflux.sample.util.Converter;
import com.marbor112.webflux.sample.util.JsonConverter;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by SG0221151 on 7/1/2017.
 */
public class Response {
    private static final Converter DEFAULT_CONVERTER = new JsonConverter();

    public static <V> ResponseEntity<String> ok(V input) {
        return ok(input, DEFAULT_CONVERTER);
    }

    public static ResponseEntity<String> notFound(String message) {
        return notFound(message, DEFAULT_CONVERTER);
    }

    public static <V> ResponseEntity<String> ok(V input, Converter converter) {
        String result = converter.convert(new OkResponse<>(input));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    public static ResponseEntity<String> notFound(String message, Converter converter) {
        String result = converter.convert(new NotFoundResponse(message));
        return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    }

    @Data
    private static class OkResponse<T> {
        private final int status = HttpStatus.OK.value();
        private final String message = HttpStatus.OK.getReasonPhrase();
        private final T result;

        OkResponse(T result) {
            this.result = result;
        }
    }

    @Data
    private static class NotFoundResponse {
        private final int status = HttpStatus.NOT_FOUND.value();
        private final String message;

        NotFoundResponse(String message) {
            this.message = message;
        }
    }

}
