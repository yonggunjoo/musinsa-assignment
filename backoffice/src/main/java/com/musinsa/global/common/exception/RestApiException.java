package com.musinsa.global.common.exception;

import com.musinsa.global.common.exception.errorcode.ApiErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RestApiException extends RuntimeException {
    private final ApiErrorCode apiErrorCode;
}
