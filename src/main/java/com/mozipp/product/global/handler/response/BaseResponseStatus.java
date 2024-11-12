package com.mozipp.product.global.handler.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum BaseResponseStatus {

    /**
     * 200 : 요청 성공
     */
    SUCCESS(true, 200, HttpStatus.OK, "요청 성공"),

    /**
     * 400 BAD_REQUEST 잘못된 요청
     */
    BAD_REQUEST(false, 400, HttpStatus.BAD_REQUEST, "잘못된 요청"),

    /**
     * 401 UNAUTHORIZED 권한없음(인증 실패)
     */
    UNAUTHORIZED(false, 401, HttpStatus.UNAUTHORIZED, "인증에 실패"),

    /**
     * 403 FORBIDDEN 권한없음
     */
    FORBIDDEN(false, 403, HttpStatus.FORBIDDEN, "권한이 없음"),

    /**
     * 404 NOT_FOUND 잘못된 리소스 접근
     */
    NOT_FOUND(false, 404, HttpStatus.NOT_FOUND, "요청한 리소스를 찾을 수 없음"),
    NOT_FOUND_RESERVATION_REQUEST(false, 40401, HttpStatus.NOT_FOUND, "해당 Reservation Request를 찾을 수 없음"),
    NOT_FOUND_DESIGNER_PRODUCT(false, 40402, HttpStatus.NOT_FOUND, "해당 Designer Product를 찾을 수 없음"),
    NOT_FOUND_RESERVATION(false, 40403, HttpStatus.NOT_FOUND, "해당 Reservation을 찾을 수 없음"),

    /**
     * 409 CONFLICT 중복된 리소스
     */
    CONFLICT(false, 409, HttpStatus.CONFLICT, "중복된 리소스 입력"),
    DUPLICATE_ID(false, 40901, HttpStatus.CONFLICT, "중복된 아이디"),

    /**
     * 500 INTERNAL_SERVER_ERROR 서버 내부 에러
     */
    INTERNAL_SERVER_ERROR(false, 500, HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 에러");

    private final boolean isSuccess;
    private final int code;
    private final HttpStatus httpStatus;
    private final String message;

}
