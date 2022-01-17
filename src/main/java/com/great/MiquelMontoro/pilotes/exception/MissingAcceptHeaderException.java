package com.great.MiquelMontoro.pilotes.exception;

import lombok.Data;

@Data
public class MissingAcceptHeaderException extends IllegalArgumentException {

    private static final long serialVersionUID = 3922532886761848732L;

    private String messageId;

    public MissingAcceptHeaderException() {
    }

    public MissingAcceptHeaderException(String s) {
        super(s);
    }

    public MissingAcceptHeaderException(String s, String messageId) {
        super(s);
        this.messageId = messageId;
    }
}