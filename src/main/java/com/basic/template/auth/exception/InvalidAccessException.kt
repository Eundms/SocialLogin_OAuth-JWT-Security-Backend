package com.basic.template.auth.exception

import com.basic.template.common.exception.ForbiddenException


class InvalidAccessException : ForbiddenException {
    constructor(message: String) : super(message)
    constructor() : super("해당 리소스에 대한 권한이 없습니다.")
}
