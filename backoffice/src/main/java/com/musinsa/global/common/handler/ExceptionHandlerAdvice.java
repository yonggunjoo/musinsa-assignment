package com.musinsa.global.common.handler;

import com.musinsa.global.common.exception.ErrorResponse;
import com.musinsa.global.common.exception.RestApiException;
import com.musinsa.global.common.exception.errorcode.ApiErrorCode;
import com.musinsa.global.common.exception.errorcode.BaseErrorCode;
import com.musinsa.global.common.exception.errorcode.CommonErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @Order(1)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("[MethodArgumentNotValidException] cause: {}, message: {}", NestedExceptionUtils.getMostSpecificCause(e), e.getMessage());
        BaseErrorCode baseErrorCode = CommonErrorCode.INVALID_ARGUMENT_ERROR;
        ErrorResponse errorResponse = ErrorResponse.of(baseErrorCode.getHttpStatus(),
                baseErrorCode.getCode(),
                baseErrorCode.getMessage(),
                e.getBindingResult());
        return ResponseEntity.status(baseErrorCode.getHttpStatus()).body(errorResponse);
    }

    @Order(2)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("[HttpMessageNotReadableException] cause: {}, message: {}", NestedExceptionUtils.getMostSpecificCause(e), e.getMessage());
        BaseErrorCode baseErrorCode = CommonErrorCode.INVALID_FORMAT_ERROR;
        ErrorResponse errorResponse = ErrorResponse.of(baseErrorCode.getHttpStatus(), baseErrorCode.getCode(), baseErrorCode.getMessage());
        return ResponseEntity.status(baseErrorCode.getHttpStatus()).body(errorResponse);
    }

    @Order(3)
    @ExceptionHandler(RestApiException.class)
    public ResponseEntity handleSystemException(RestApiException e) {
        log.error("[SystemException] cause: {}, message: {}", NestedExceptionUtils.getMostSpecificCause(e), e.getMessage());
        ApiErrorCode baseErrorCode = e.getApiErrorCode();
        ErrorResponse errorResponse = ErrorResponse.of(baseErrorCode.getHttpStatus(), baseErrorCode.getCode(), baseErrorCode.getMessage());
        return ResponseEntity.status(baseErrorCode.getHttpStatus()).body(errorResponse);
    }

    @Order(4)
    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e) {
        log.error("[Exception] cause: {} , message: {}", NestedExceptionUtils.getMostSpecificCause(e), e.getMessage());
        BaseErrorCode baseErrorCode = CommonErrorCode.INTERNAL_SERVER_ERROR;
        ErrorResponse errorResponse = ErrorResponse.of(baseErrorCode.getHttpStatus(), baseErrorCode.getCode(), baseErrorCode.getMessage());
        return ResponseEntity.status(baseErrorCode.getHttpStatus()).body(errorResponse);
    }
}