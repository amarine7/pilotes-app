package com.great.MiquelMontoro.pilotes.exception;

import com.great.MiquelMontoro.pilotes.dto.Order;

public class MissingOrderDetailsException extends IllegalArgumentException {

    public MissingOrderDetailsException() {
    }
    public MissingOrderDetailsException(String s) {
        super(s);
    }

    public MissingOrderDetailsException(String s, Order order) {
        super(s);
//        if (order.getNumberOfPilotes() == null) {
//            throw new MissingOrderDetailsException("The order is not valid: missing ", order);
//        }
//        if (order.getNumberOfPilotes() == null || StringUtils.isEmpty(order.getDeliveryAddress())) {
//            throw new MissingOrderDetailsException("The order is not valid: missing ", order);
//        }
    }
}
