package com.great.MiquelMontoro.pilotes.service;

import com.great.MiquelMontoro.pilotes.config.OrderConfig;
import com.great.MiquelMontoro.pilotes.mapper.OrderMapper;
import com.great.MiquelMontoro.pilotes.model.Order;
import com.great.MiquelMontoro.pilotes.security.repo.UserRepo;
import com.great.MiquelMontoro.pilotes.util.LogUtils;
import com.great.MiquelMontoro.pilotes.util.PilotesConstants;
import com.great.MiquelMontoro.pilotes.exception.TooLateToUpdateOrderException;
import com.great.MiquelMontoro.pilotes.repo.PilotesRepository;
import com.great.MiquelMontoro.pilotes.security.config.UserConfig;
import com.great.MiquelMontoro.pilotes.security.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.great.MiquelMontoro.pilotes.specifications.OrderSpecifications.*;
import static org.springframework.data.jpa.domain.Specification.where;

@Service
@Transactional
@Slf4j
public class PilotesService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    PilotesRepository pilotesRepository;

    @Autowired
    private UserRepo userRepo;

    public com.great.MiquelMontoro.pilotes.dto.Order createPilotes(String messageId, com.great.MiquelMontoro.pilotes.dto.Order order) {

        Order pilotesOrder = orderMapper.mapOrder(order);

        processOrder(pilotesOrder);
        pilotesRepository.save(pilotesOrder);

        String logMsg = LogUtils.prepareLogMessage(messageId, PilotesConstants.PILOTES_CREATED, null, null);
        log.info(logMsg);

        return orderMapper.mapOrderInverse(pilotesOrder);
    }

    public com.great.MiquelMontoro.pilotes.dto.Order updatePilotes(String messageId, Long id, com.great.MiquelMontoro.pilotes.dto.Order order) {

        Order record = pilotesRepository.getOne(id);
//        Optional<com.laurentiuspilca.ssia.model.Order> optionalrecord = pilotesRepository.findById(id);
//        if(!optionalrecord.isPresent()) {
//            throw new EtcException("");
//        }
//        com.laurentiuspilca.ssia.model.Order  record = optionalrecord.get();

        if (!OrderConfig.stillInTimeToUpdateOrder(record)) {
            throw new TooLateToUpdateOrderException("Sorry, it's too late to update your pilotes order.");
        } else {
            orderMapper.updateRecord(order, record);
            processOrder(record);
            pilotesRepository.save(record);

            String logMsg = LogUtils.prepareLogMessage(messageId, PilotesConstants.PILOTES_UPDATED, null, null);
            log.info(logMsg);

            return orderMapper.mapOrderInverse(record);
        }
    }

    public List<com.great.MiquelMontoro.pilotes.dto.Order> findPilotes(String messageId, String username, Long customerId, Long orderId, String firstName, String lastName, String deliveryAddress) {

        Specification<Order> specifications = where(hasId(orderId))
                                                    .and(deliveryAddressLike(deliveryAddress))
                                                    .and(hasCustomerId(customerId))
                                                    .and(firstNameLike(firstName))
                                                    .and(lastNameLike(lastName))
                                                    .and(distinct());
        List<Order> records = pilotesRepository.findAll(specifications);

        String logMsg = LogUtils.prepareLogMessage(messageId, PilotesConstants.PILOTES_SEARCHED, username, null);
        log.info(logMsg);

        return orderMapper.mapOrdersInverse(records);
    }

    private void processOrder(Order pilotesOrder) {
        addOrderToAuthenticateUserList(pilotesOrder);
        pilotesOrder.setCreationDate(LocalDateTime.now());
        pilotesOrder.setOrderTotal(OrderConfig.tallyUp(pilotesOrder.getNumberOfPilotes()));
    }

    private void addOrderToAuthenticateUserList(Order pilotesOrder) {
        Authentication authentication = UserConfig.retrieveUserAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String username = authentication.getName();
            User user = User.builder()
                    .id(userRepo.findUserByUsername(username).get().getId())
                    .username(username)
                    .build();
            pilotesOrder.setUser(user);
        }
    }





    // to remove eventually
    public List<Order> returnRecords() {
        return pilotesRepository.findAll();
//        List<com.laurentiuspilca.ssia.model.Order> list = pilotesRepository.findAll();
//        System.out.println(list.get(0).getUser());
//        return list;
    }
}
