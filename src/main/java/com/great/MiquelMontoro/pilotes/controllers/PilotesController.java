package com.great.MiquelMontoro.pilotes.controllers;

import com.great.MiquelMontoro.pilotes.config.OrderConfig;
import com.great.MiquelMontoro.pilotes.dto.Order;
import com.great.MiquelMontoro.pilotes.exception.MissingAcceptHeaderException;
import com.great.MiquelMontoro.pilotes.exception.MissingOrderDetailsException;
import com.great.MiquelMontoro.pilotes.service.PilotesService;
import com.great.MiquelMontoro.pilotes.util.LogUtils;
import com.great.MiquelMontoro.pilotes.util.PilotesConstants;
import com.great.MiquelMontoro.pilotes.security.config.UserConfig;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class  PilotesController implements PilotesApi {

    private final HttpServletRequest request;

    private static final Logger log = LoggerFactory.getLogger(PilotesController.class);

    public PilotesController(HttpServletRequest request) {
        this.request = request;
    }

    @Autowired
    PilotesService pilotesService;

    @Override
    public ResponseEntity<Order> createPilotes(String contentType, Order order) {

        String messageId = LogUtils.setMessageId(request.getHeader(LogUtils.MESSAGE_ID));
        if (contentType != null && contentType.contains("application/json")) {

            if (!OrderConfig.checkNumberOfPilotes(order.getNumberOfPilotes()) || StringUtils.isEmpty(order.getDeliveryAddress())) {
                throw new MissingOrderDetailsException("The order is not valid!", order);
            }
            try {

                long startTime = System.currentTimeMillis();
                String logMsg = LogUtils.prepareLogMessage(messageId, PilotesConstants.CREATE_PILOTES, null, null);
                log.info(logMsg);

                Order orderedPilotes = pilotesService.createPilotes(messageId, order);

                long endTime = System.currentTimeMillis();
                logMsg = LogUtils.prepareLogMessage(messageId, PilotesConstants.CREATE_PILOTES, null, String.valueOf(endTime - startTime));
                log.info(logMsg);

                return new ResponseEntity<Order>(orderedPilotes, HttpStatus.OK);
            } catch (Exception e) {
                String log_msg = LogUtils.prepareLogMessage(messageId, PilotesConstants.ERROR_DETAIL, null, null);
                log.error(log_msg, e);
                throw e;
            }
        }
        throw new MissingAcceptHeaderException("Content-Type", contentType);
    }

    @Override
    public ResponseEntity<Order> updatePilotes(String contentType, Long id, Order order) {

        String messageId = LogUtils.setMessageId(request.getHeader(LogUtils.MESSAGE_ID));
        if (contentType != null && contentType.contains("application/json")) {

            try {
                long startTime = System.currentTimeMillis();
                String logMsg = LogUtils.prepareLogMessage(messageId, PilotesConstants.UPDATE_PILOTES, null, null);
                log.info(logMsg);

                Order updatedPilotes = pilotesService.updatePilotes(messageId, id, order);

                long endTime = System.currentTimeMillis();
                logMsg = LogUtils.prepareLogMessage(messageId, PilotesConstants.UPDATE_PILOTES, null, String.valueOf(endTime - startTime));
                log.info(logMsg);

                return new ResponseEntity<Order>(updatedPilotes, HttpStatus.OK);
            } catch (Exception e) {
                String log_msg = LogUtils.prepareLogMessage(messageId, PilotesConstants.ERROR_DETAIL, null, null);
                log.error(log_msg, e);
                throw e;
            }
        }
        throw new MissingAcceptHeaderException("Content-Type", contentType);
    }

    public ResponseEntity<List<Order>> findPilotes(String contentType, String authorization, Long customerId, Long orderId, String firstName, String lastName, String deliveryAddress) {
        String messageId = LogUtils.setMessageId(request.getHeader(LogUtils.MESSAGE_ID));
        String username = UserConfig.retrieveUserAuthentication().getName();
        if (contentType != null && contentType.contains("application/json")) {

            try {

                long startTime = System.currentTimeMillis();
                String logMsg = LogUtils.prepareLogMessage(messageId, PilotesConstants.SEARCH_PILOTES, username, null);
                log.info(logMsg);

                List<Order> orders = pilotesService.findPilotes(messageId, username, customerId, orderId, firstName, lastName, deliveryAddress);

                long endTime = System.currentTimeMillis();
                logMsg = LogUtils.prepareLogMessage(messageId, PilotesConstants.SEARCH_PILOTES, username, String.valueOf(endTime - startTime));
                log.info(logMsg);

                return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
            } catch (Exception e) {
                String log_msg = LogUtils.prepareLogMessage(messageId, PilotesConstants.ERROR_DETAIL, username, null);
                log.error(log_msg, e);
                throw e;
            }
        }
        throw new MissingAcceptHeaderException("Content-Type", contentType);
    }

//    @GetMapping("/test")
//    public String test() {
//        return "Test";
//    }
//
//    @GetMapping("/returnRecords")
//    public List<com.great.MiquelMontoro.pilotes.model.Order> checkLoadedRecords() {
//        return pilotesService.returnRecords();
//    }
}
