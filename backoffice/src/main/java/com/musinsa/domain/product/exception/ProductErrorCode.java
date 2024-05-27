package com.musinsa.domain.product.exception;

import com.musinsa.global.common.exception.errorcode.ApiErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ProductErrorCode implements ApiErrorCode {
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "P001", "요청은 null일 수 없습니다."),
    BRANDNAME_BAD_REQUEST(HttpStatus.BAD_REQUEST, "P002", "브랜드명을 입력하세요."),
    CATEGORY_NAME_BAD_REQUEST(HttpStatus.BAD_REQUEST, "P003", "카테고리명을 입력하세요."),
    PRICE_BAD_REQUEST(HttpStatus.BAD_REQUEST, "P004", "가격을 입력하세요. 0 보다 커야 합니다."),
    ALREADY_EXISTS(HttpStatus.CONFLICT, "P005", "이미 등록된 상품입니다."),
    SAVE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "P006", "상품 등록에 실패했습니다."),
    ID_BAD_REQUEST(HttpStatus.BAD_REQUEST, "P007", "상품 아이디를 입력하세요."),
    NOT_REGISTRED(HttpStatus.CONFLICT, "C001", "등록되지 않은 상품 입니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}

