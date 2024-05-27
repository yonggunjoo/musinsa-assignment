package com.musinsa.domain.category.exception;

import com.musinsa.global.common.exception.errorcode.ApiErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CategoryErrorCode implements ApiErrorCode {
    NOT_REGISTRED(HttpStatus.CONFLICT, "C001", "등록되지 않은 카테고리 입니다."),
    NOT_FOUNT(HttpStatus.NOT_FOUND, "C005", "카테고리 정보 조회에 실패했습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}

