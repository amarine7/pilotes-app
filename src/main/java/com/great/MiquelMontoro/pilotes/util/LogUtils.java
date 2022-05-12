package com.great.MiquelMontoro.pilotes.util;

import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


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

//    public static void java() {
//        List list = List.of(2);
//
//        Integer[] sourceArray = { 0, 1, 2, 3, 4, 5 };
//        List<Integer> targetList = Arrays.asList(sourceArray);
//        targetList = targetList.stream().collect(Collectors.toList());
//        s.toCharArray()
//
//        Optional.of(targetList);
//        int six = Integer.valueOf(targetList.get(0));
//
//        String s = new "v";
//        s.toCharArray();
//        System.out.println("");
//    }
}
