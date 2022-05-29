package com.great.MiquelMontoro.pilotes.mapper;

import com.great.MiquelMontoro.pilotes.model.Order;
import com.great.MiquelMontoro.pilotes.model.Order.OrderBuilder;
import com.great.MiquelMontoro.pilotes.security.model.User;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-29T04:35:34+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 13.0.1 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public Order mapOrder(com.great.MiquelMontoro.pilotes.dto.Order order) {

        OrderBuilder order1 = Order.builder();

        if ( order != null ) {
            order1.deliveryAddress( order.getDeliveryAddress() );
            order1.numberOfPilotes( order.getNumberOfPilotes() );
        }

        return order1.build();
    }

    @Override
    public com.great.MiquelMontoro.pilotes.dto.Order mapOrderInverse(Order order) {

        com.great.MiquelMontoro.pilotes.dto.Order.OrderBuilder order1 = com.great.MiquelMontoro.pilotes.dto.Order.builder();

        if ( order != null ) {
            if ( order.getCreationDate() != null ) {
                order1.orderDate( DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm" ).format( order.getCreationDate() ) );
            }
            order1.idUser( orderUserId( order ) );
            order1.id( order.getId() );
            order1.deliveryAddress( order.getDeliveryAddress() );
            order1.numberOfPilotes( order.getNumberOfPilotes() );
            order1.orderTotal( order.getOrderTotal() );
        }

        return order1.build();
    }

    @Override
    public List<com.great.MiquelMontoro.pilotes.dto.Order> mapOrdersInverse(List<Order> orders) {
        if ( orders == null ) {
            return null;
        }

        List<com.great.MiquelMontoro.pilotes.dto.Order> list = new ArrayList<com.great.MiquelMontoro.pilotes.dto.Order>( orders.size() );
        for ( Order order : orders ) {
            list.add( mapOrderInverse( order ) );
        }

        return list;
    }

    @Override
    public void updateRecord(com.great.MiquelMontoro.pilotes.dto.Order update, Order oldRecord) {
        if ( update == null ) {
            return;
        }

        if ( update.getDeliveryAddress() != null ) {
            oldRecord.setDeliveryAddress( update.getDeliveryAddress() );
        }
        if ( update.getNumberOfPilotes() != null ) {
            oldRecord.setNumberOfPilotes( update.getNumberOfPilotes() );
        }
    }

    private Long orderUserId(Order order) {
        if ( order == null ) {
            return null;
        }
        User user = order.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
