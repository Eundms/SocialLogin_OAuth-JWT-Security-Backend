package com.basic.template.common.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.UNAUTHORIZED)
abstract class UnauthorizedException(message: String) : ApiException(message, HttpStatus.UNAUTHORIZED)
