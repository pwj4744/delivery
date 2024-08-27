package org.delivery.api.domain.token.service;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.token.ifs.TokenHelperIfs;
import org.delivery.api.domain.token.model.TokenDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

/**
 * token 에 대한 도메인로직 (토큰만 처리)
 */
@RequiredArgsConstructor
@Service
public class TokenService {
    //userid를 받아서
    private final TokenHelperIfs tokenHelperIfs;

    public TokenDto issueAccessToken(Long userId){
        var data = new HashMap<String, Object>();
        data.put("userId", userId);
        return tokenHelperIfs.issueAccessToken(data);
    }

    public TokenDto issueRefreshToken(Long userId){
        var data = new HashMap<String, Object>();
        data.put("userId", userId);
        return tokenHelperIfs.issueRefreshToken(data);
    }

    public Long validationToken(String token){
        var map = tokenHelperIfs.validationTokenWithThrow(token);

        var userId = map.get("userId"); //토큰에서 값을 꺼내와 userId에 리턴
        Objects.requireNonNull(userId, ()->{throw new ApiException(ErrorCode.NULL_POINT);}); // Objects의 활용법, 값이 없을 수도 있으니 null처리

        return Long.parseLong(userId.toString());
    }

}
