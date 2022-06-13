package com.great.MiquelMontoro.pilotes.exception;

import com.great.MiquelMontoro.pilotes.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MissingRequestHeaderException.class)
    protected ResponseEntity<ErrorResponse> handleMissingRequestHeaderException(Exception ex) {
        log.error(ex.getMessage());
        return ResponseEntity
                .badRequest()
                .body(ErrorResponse
                        .builder()
                        .timestamp(new Date().toString())
                        .error(ex.getMessage())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .build());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {


        String errorMessage = ex.getBindingResult()
                .getFieldErrors().get(0).getDefaultMessage();
        List<String> validationList = ex
        .getBindingResult()
        .getFieldErrors()
        .stream()
        .map(fE -> fE.getField() + " " + fE.getDefaultMessage())
        .collect(Collectors.toList());

//        List<FieldError> validationList2 = ex.getBindingResult().getFieldErrors();
//        FieldError fe2 = validationList2.get(0);
//        fe2.()
        log.error("Validation error list : " + validationList);

//        ApiErrorVO apiErrorVO = new ApiErrorVO(errorMessage);
//        apiErrorVO.setErrorList(validationList);

        return ResponseEntity
                .badRequest()
                .body(ErrorResponse
                        .builder()
                        .timestamp(new Date().toString())
                        .error("Validation error list : " + validationList)
                        .status(HttpStatus.BAD_REQUEST.value())
                        .build());
    }



    @Override
    protected ResponseEntity<Object>
    handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                 HttpHeaders headers,
                                 HttpStatus status,
                                 WebRequest request) {
        log.error("Validation error list: required request body for parameter 'order' is missing");
        return ResponseEntity
                .badRequest()
                .body(ErrorResponse
                        .builder()
                        .timestamp(new Date().toString())
                        .error("Required request body for parameter 'order' is missing")
                        .status(HttpStatus.BAD_REQUEST.value())
                        .build());
    }



//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ResponseBody
//    @ExceptionHandler(MissingAcceptHeaderException.class)
//    public ErrorResponse handleMissingRequestHeaderException(Exception ex, WebRequest request) {
//
//        MissingAcceptHeaderException exception = (MissingAcceptHeaderException)ex;
//        log.error(ex.getMessage() + ". MessageId: " + exception.getMessageId());
//
//        return ErrorResponse
//                .builder()
//                .timestamp(new Date().toString())
//                .error(ex.getMessage())
//                .status(HttpStatus.BAD_REQUEST.value())
//                .build();
//    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ResponseBody
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ErrorResponse handleMethodArgumentNotValidException(Exception ex, WebRequest request) {
//        log.error(ex.getMessage());
//        return ErrorResponse
//                .builder()
//                .timestamp(new Date().toString())
//                .error(ex.getMessage())
//                .status(HttpStatus.BAD_REQUEST.value())
//                .build();
//    }
}
