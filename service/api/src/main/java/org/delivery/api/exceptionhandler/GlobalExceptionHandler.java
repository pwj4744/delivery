package org.delivery.api.exceptionhandler;

import lombok.extern.slf4j.Slf4j;
import org.delivery.api.common.api.Api;
import org.delivery.api.common.error.ErrorCode;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice //예외는 이곳으로 끌어모으겠다.
@Order(value = Integer.MAX_VALUE)   // 가장 마지막에 실행 적용, 최저값일수록 먼저 실행.
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class) // 모든예외 다잡기위해.
    public ResponseEntity<Api<Object>> exception (
        Exception exception
    ){
        log.error("",exception);

        return ResponseEntity
            .status(500)
            .body(
                Api.ERROR(ErrorCode.SERVER_ERROR)
            );
    }
}
