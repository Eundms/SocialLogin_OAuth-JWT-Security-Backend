package com.basic.template.security.oauth.provider;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OAuthProvider {
    GOOGLE("google"),
    KAKAO("kakao");

    private String provider;


}
