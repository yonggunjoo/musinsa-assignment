package com.musinsa.domain.category.exception;

import com.musinsa.global.common.exception.errorcode.ApiErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CategoryErrorCode implements ApiErrorCode {
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "E001", "카테고리명 파라미터가 입력되지 않았습니다.."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "E002", "해당 카테고리의 상품을 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}

