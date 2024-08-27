package org.delivery.api.common.exception;

import lombok.Getter;
import org.delivery.api.common.error.ErrorCodeIfs;

@Getter // implements 에서 메소드를 getter로 받아줄수 있다.
public class ApiException extends RuntimeException implements ApiExceptionIfs {

    private final ErrorCodeIfs errorCodeIfs;
    private final String errorDescription; //상세한 에러메시지

    //생성자
    public ApiException(ErrorCodeIfs errorCodeIfs){
        super(errorCodeIfs.getDescription()); // 부모에 디스크립션 올려줌
        this.errorCodeIfs = errorCodeIfs;
        this.errorDescription = errorCodeIfs.getDescription();
    }

    public ApiException(ErrorCodeIfs errorCodeIfs, String errorDescription){
        super(errorDescription); // 내가 정의한것을 직접 런타임익셉션에 올려준다.
        this.errorCodeIfs = errorCodeIfs;
        this.errorDescription = errorDescription;
    }

    public ApiException(ErrorCodeIfs errorCodeIfs, Throwable tx){
        super(tx); // 부모에게 Throwable
        this.errorCodeIfs = errorCodeIfs;
        this.errorDescription = errorCodeIfs.getDescription();
    }

    public ApiException(ErrorCodeIfs errorCodeIfs, Throwable tx, String errorDescription){
        super(tx);
        this.errorCodeIfs = errorCodeIfs;
        this.errorDescription = errorDescription;
    }
}
