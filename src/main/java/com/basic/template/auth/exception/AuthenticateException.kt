package com.basic.template.auth.exception

import org.springframework.security.core.AuthenticationException

class AuthenticateException(message: String) : AuthenticationException(message)

/*
*     NOT_FOUND_AUTHORITY("NOT_FOUND_AUTHORITY", "존재하지 않는 권한입니다.",HttpStatus.BAD_REQUEST),
    GOOGLE_CONNECTION_ERROR("GOOGLE_CONNECTION_ERROR", "구글 서버와의 연결에 실패하였습니다.", HttpStatus.GATEWAY_TIMEOUT),
    NO_AUTHORIZATION_CODE("NO_AUTHORIZATION_CODE", "요청에 인가 코드가 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
* */