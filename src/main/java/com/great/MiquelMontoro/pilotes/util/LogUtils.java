package com.great.MiquelMontoro.pilotes.util;

import org.springframework.util.StringUtils;
import java.util.UUID;


public class LogUtils {

    public static final String MESSAGE_ID = "Some numeric operation identifier that may already have been assigned to the request";

    public static String prepareLogMessage(String messageId, String operation, String username, String responseTime) {
        StringBuilder sb = new StringBuilder();
        if (!StringUtils.isEmpty(messageId)) {
            sb.append("Message ID: ");
            sb.append(messageId);
            sb.append("|");
        }

        sb.append("Operation: ");
        sb.append(operation);
        sb.append("|");

        sb.append("Username: ");
        // TODO add check if the user is authenticated
        sb.append(username);
        sb.append("|");

        // Check if Response time has been passed
        if (!StringUtils.isEmpty(responseTime)) {
            sb.append("Response Time: ");
            sb.append(responseTime);
            sb.append(" ms|");
        }

        return sb.toString();
    }

    public static String setMessageId(String header) {
        return (StringUtils.isEmpty(header)) ? UUID.randomUUID().toString() : header;
    }
}
