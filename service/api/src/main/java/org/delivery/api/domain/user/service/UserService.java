package org.delivery.api.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.api.Api;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.error.UserErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.db.user.UserEntity;
import org.delivery.db.user.UserRepository;
import org.delivery.db.user.enums.UserStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * User 도메인 로직을 처리 하는 서비스
 */
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    //에러가 나면 롤백되어야하니까 transaction 붙여줘도됨.
    public UserEntity register(UserEntity userEntity){ // 항상 UserEntity로만 받겠다.
        return Optional.ofNullable(userEntity) //ofNullable로 처리한다.
            .map(it ->{
                userEntity.setStatus(UserStatus.REGISTERED);
                userEntity.setRegisteredAt(LocalDateTime.now());
                return userRepository.save(userEntity);
            })
            .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "User Entity Null"));
    }

    public UserEntity login(
        String email,
        String password
    ){
        var entity = getUserWithThrow(email, password);
        return entity;
    }

    public UserEntity getUserWithThrow(
        String email,
        String password
    ){
        return userRepository.findFirstByEmailAndPasswordAndStatusOrderByIdDesc( //등록된 사용자 있는지 체크
            email,
            password,
            UserStatus.REGISTERED
        ).orElseThrow(()-> new ApiException(UserErrorCode.USER_NOT_FOUND));
    }

    public UserEntity getUserWithThrow(
        Long userId
    ){
        return userRepository.findFirstByIdAndStatusOrderByIdDesc(
            userId,
            UserStatus.REGISTERED
        ).orElseThrow(()-> new ApiException(UserErrorCode.USER_NOT_FOUND));
    }
}
