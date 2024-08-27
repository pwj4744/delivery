package org.delivery.db.user.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserStatus {

    //AllargsConstructor떄문에 이게 가능함.  , 1. 오탈자 방지 2.
    REGISTERED("등록"),
    UNREGISTERED("해지"),
    ;

    private final String description;
}
