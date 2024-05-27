package com.musinsa.global.common.exception.errorcode;

import org.springframework.http.HttpStatus;

public interface ApiErrorCode {
    String name();

    HttpStatus getHttpStatus();

    String getCode();

    String getMessage();
}