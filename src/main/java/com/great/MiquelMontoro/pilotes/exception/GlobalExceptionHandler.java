package com.great.MiquelMontoro.pilotes.exception;

import com.great.MiquelMontoro.pilotes.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    @ExceptionHandler(MissingAcceptHeaderException.class)
    public ErrorResponse handleMissingRequestHeaderException(Exception ex, WebRequest request) {

        MissingAcceptHeaderException exception = (MissingAcceptHeaderException)ex;
        log.error(ex.getMessage() + ". MessageId: " + exception.getMessageId());

        return ErrorResponse
                .builder()
                .timestamp(new Date().toString())
                .error(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .build();
    }
}
