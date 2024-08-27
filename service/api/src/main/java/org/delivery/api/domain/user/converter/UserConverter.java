package org.delivery.api.domain.user.converter;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Converter;
import org.delivery.api.common.api.Api;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.user.controller.model.UserRegisterRequest;
import org.delivery.api.domain.user.controller.model.UserResponse;
import org.delivery.db.user.UserEntity;

import java.util.Optional;

@RequiredArgsConstructor
@Converter
public class UserConverter {

    public UserEntity toEntity(UserRegisterRequest request){
        //리퀘스트가 옵셔널일 수 있다.
        return Optional.ofNullable(request)
            .map(it ->{
                // to entity
                return UserEntity.builder()
                    .name(request.getName())
                    .email(request.getEmail())
                    .password(request.getPassword())
                    .address(request.getAddress())
                    .build(); // 리퀘스트 > 엔티티에 값 매핑 해서 리턴
            })
            .orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT, "UserRegisterRequest Null"));
    }

    public UserResponse toResponse(UserEntity userEntity) {
    //항상 null 체크
        return Optional.ofNullable(userEntity)
            .map(it ->{
                // to response
                return UserResponse.builder() //빌더패턴
                    .id(userEntity.getId())
                    .name(userEntity.getName())
                    .status(userEntity.getStatus())
                    .email(userEntity.getEmail())
                    .address(userEntity.getAddress())
                    .registeredAt(userEntity.getRegisteredAt())
                    .unregisteredAt(userEntity.getUnregisteredAt())
                    .lastLoginAt(userEntity.getLastLoginAt())
                    .build();
            })
            .orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT, "UserEntity Null"));

    }
}
