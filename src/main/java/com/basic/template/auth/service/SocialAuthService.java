package com.basic.template.auth.service;


import com.basic.template.auth.dto.OAuthTokenDto;
import com.basic.template.auth.dto.OAuthUserInfoDto;

public abstract class SocialAuthService {
    // 프론트에서 받은 credential 이용해서, 구글로부터 access_token과 id_token 받기
    abstract public OAuthTokenDto getToken(String code);
    // 발급받은 access_token으로 구글로부터 유저정보 조회 요청
    abstract public OAuthUserInfoDto getUserInfo(OAuthTokenDto dto);
    // 요청한 유저 정보 반환 -> 로그인 서비스에서 진행

}
