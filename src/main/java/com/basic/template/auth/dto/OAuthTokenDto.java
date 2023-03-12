package com.basic.template.auth.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OAuthTokenDto {
    private String token_type;
    private String access_token;
    private String expires_in;
    private String refresh_token;
    private String refresh_token_expires_in;
    private String scope;
    private String id_token;
}
