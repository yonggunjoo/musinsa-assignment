package com.musinsa.global.common.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

@Getter
@RequiredArgsConstructor
@Builder
public class ErrorResponse {
    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final List<ValidationError> errors;

    public static ErrorResponse of(HttpStatus httpStatus, int code, String message) {
        return ErrorResponse.builder()
                .httpStatus(httpStatus)
                .code(String.valueOf(code))
                .message(message)
                .build();
    }

    public static ErrorResponse of(HttpStatus httpStatus, String code, String message) {
        return ErrorResponse.builder()
                .httpStatus(httpStatus)
                .code(code)
                .message(message)
                .build();
    }

    public static ErrorResponse of(HttpStatus httpStatus, int code, String message, BindingResult bindingResult) {
        return ErrorResponse.builder()
                .httpStatus(httpStatus)
                .code(String.valueOf(code))
                .message(message)
                .errors(ValidationError.of(bindingResult))
                .build();
    }

    @Getter
    public static class ValidationError {
        private final String field;
        private final String value;
        private final String message;

        private ValidationError(FieldError fieldError) {
            this.field = fieldError.getField();
            this.value = fieldError.getRejectedValue() == null ? "" : fieldError.getRejectedValue().toString();
            this.message = fieldError.getDefaultMessage();
        }

        public static List<ValidationError> of(final BindingResult bindingResult) {
            return bindingResult.getFieldErrors().stream()
                    .map(ValidationError::new)
                    .toList();
        }

    }

}
