package com.musinsa.global.common.exception.errorcode;

import org.springframework.http.HttpStatus;

public interface BaseErrorCode {
    String name();

    HttpStatus getHttpStatus();

    int getCode();

    String getMessage();
}