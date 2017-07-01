package com.marbor112.webflux.sample.controllers.responses;

import com.marbor112.webflux.sample.util.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by SG0221151 on 7/1/2017.
 */
public class Response {
    private static final Converter converter = new Converter();

    public static <V> ResponseEntity<String> ok(V input) {
        String result = converter.toJson(new OkResponse<>(input));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    public static ResponseEntity<String> notFound(String message) {
        String result = converter.toJson(new NotFoundResponse(message));
        return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    }


    private static class OkResponse<T> {
        private final int status = HttpStatus.OK.value();
        private final String message = HttpStatus.OK.getReasonPhrase();
        private final T result;

        OkResponse(T result) {
            this.result = result;
        }

        public int getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }

        public T getResult() {
            return result;
        }
    }

    private static class NotFoundResponse {
        private final int status = HttpStatus.OK.value();
        private final String message;

        NotFoundResponse(String message) {
            this.message = message;
        }

        public int getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }
    }

}
