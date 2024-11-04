package com.mozipp.product.global.handler;

import com.mozipp.product.global.handler.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<BaseResponse> handleBaseException(BaseException e) {
        return ResponseEntity.status(e.getStatus().getHttpStatus()).body(BaseResponse.fail(e.getStatus()));
    }
}
