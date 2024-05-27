package com.musinsa.domain.brand.exception;

import com.musinsa.global.common.exception.errorcode.ApiErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum BrandErrorCode implements ApiErrorCode {
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "B001", "올바르지 않은 파라미터입니다."),
    ALREADY_EXISTS(HttpStatus.CONFLICT, "B002", "이미 존재하는 브랜드입니다."),
    SAVE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "B003", "브랜드 저장에 실패했습니다."),
    NOT_REGISTRED(HttpStatus.CONFLICT, "B004", "등록되지 않은 브랜드 입니다."),
    NOT_FOUNT(HttpStatus.NOT_FOUND, "B005", "브랜드 정보 조회에 실패했습니다."),
    BRANDNAME_BAD_REQUEST(HttpStatus.BAD_REQUEST, "B006", "브랜드명을 입력하세요."),
    DUPLICATE(HttpStatus.INTERNAL_SERVER_ERROR, "B003", "이미 등록된 브랜드 입니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}

