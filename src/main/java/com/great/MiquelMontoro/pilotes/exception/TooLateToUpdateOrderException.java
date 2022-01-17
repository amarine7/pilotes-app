package com.great.MiquelMontoro.pilotes.exception;

public class TooLateToUpdateOrderException extends RuntimeException {

    public TooLateToUpdateOrderException() {
    }

    public TooLateToUpdateOrderException(String s) {
        super(s);
    }
}
