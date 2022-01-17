package com.great.MiquelMontoro.pilotes.config;

import com.great.MiquelMontoro.pilotes.model.Order;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Set;

public class OrderConfig {

    public static final Float SINGLE_PILOTES_PRICE = Float.parseFloat("1.33");
    private static final Set<Integer> ALLOWED_PILOTES_ORDERS = Set.of(5, 10, 15);

    public static Boolean stillInTimeToUpdateOrder(Order record) {
        return record.getCreationDate().plus(Duration.of(5, ChronoUnit.MINUTES)).isAfter(LocalDateTime.now());
    }

    public static Boolean checkNumberOfPilotes(Integer numberOfPilotes) {
        return ALLOWED_PILOTES_ORDERS.stream().anyMatch(k -> numberOfPilotes.intValue() == k.intValue());
    }

    public static Float tallyUp(Integer numberOfPilotes) {
        return SINGLE_PILOTES_PRICE * numberOfPilotes;
//        DecimalFormat format = new DecimalFormat("##.00");
//        return Float.parseFloat(format.format(x).replace(",", "."));
    }
}
