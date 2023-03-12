package com.basic.template.config;


import com.basic.template.auth.exception.AuthenticateException;
import com.basic.template.common.dto.ErrorResponseDto;
import com.basic.template.common.exception.ApiException;
import java.time.LocalDateTime;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handleApiException(Exception exception, WebRequest request) {
        if (exception instanceof ApiException) {
            return handleExceptionInternal(exception, null, new HttpHeaders(), ((ApiException) exception).getStatus(), request);
        } else if (exception instanceof AuthenticateException) {
            return handleExceptionInternal(exception, null, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
        } else {
            return handleExceptionInternal(exception, null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
        }
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage(),
                servletWebRequest.getRequest().getRequestURI(),
                servletWebRequest.getRequest().getRemoteAddr()
        );
        return ResponseEntity.badRequest().body(errorResponseDto);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        ErrorResponseDto errorResponseDto;
        if (status == HttpStatus.INTERNAL_SERVER_ERROR) {
            errorResponseDto = new ErrorResponseDto(
                    LocalDateTime.now(),
                    status.value(),
                    status.getReasonPhrase(),
                    "Internal Server Error",
                    servletWebRequest.getRequest().getRequestURI(),
                    servletWebRequest.getRequest().getRemoteAddr()
            );
        } else {
            errorResponseDto = new ErrorResponseDto(
                    LocalDateTime.now(),
                    status.value(),
                    status.getReasonPhrase(),
                    ex.getMessage(),
                    servletWebRequest.getRequest().getRequestURI(),
                    servletWebRequest.getRequest().getRemoteAddr()
            );
        }
        return ResponseEntity.status(status).headers(headers).body(errorResponseDto);
    }
}
