package org.delivery.api.common.error;

public interface ErrorCodeIfs {
    //3가지 메소드 정의.
    Integer getHttpStatusCode();

    Integer getErrorCode();

    String getDescription();
}
