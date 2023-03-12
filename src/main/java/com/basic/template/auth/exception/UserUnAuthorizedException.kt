package com.basic.template.auth.exception

import com.basic.template.common.exception.UnauthorizedException


class UserUnAuthorizedException : UnauthorizedException {
    constructor(message: String) : super(message)
    constructor() : super("인증되지 않은 사용자 입니다.")
}
