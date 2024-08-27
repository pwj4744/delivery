package org.delivery.api.exceptionhandler;

import lombok.extern.slf4j.Slf4j;
import org.delivery.api.common.api.Api;
import org.delivery.api.common.exception.ApiException;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(value = Integer.MIN_VALUE)   // 최우선처리
public class ApiExceptionHandler {

    @ExceptionHandler(value = ApiException.class)// ApiException 모두 가져온다.
    public ResponseEntity<Api<Object>> apiException(
        ApiException apiException
    ){
        log.error("", apiException); // apiexception은 런타임 익셉션을 상속받았기 떄문에 스택트레이스 모두 찍기 가능.

        var errorCode = apiException.getErrorCodeIfs(); //

        return ResponseEntity
            .status(errorCode.getHttpStatusCode())
            .body(
                Api.ERROR(errorCode, apiException.getErrorDescription())
            );

    }
}
