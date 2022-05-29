package com.great.MiquelMontoro.pilotes.mapper;

import com.great.MiquelMontoro.pilotes.config.OrderConfig;
import com.great.MiquelMontoro.pilotes.dto.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@ExtendWith(MockitoExtension.class)
public class OrderMapperTest {

    private final OrderMapper mapper = Mappers.getMapper(OrderMapper.class);

    public static Integer numberOfPilotes = new Random(1).nextInt();
    public static Long id = new Random(2).nextLong();

    public static LocalDateTime localDateTime = LocalDateTime.now();
    public static String dateString = localDateTime.truncatedTo(ChronoUnit.MINUTES).toString().replace("T", " ");


    @Test
    public void testMapOrder() {
        com.great.MiquelMontoro.pilotes.model.Order orderEntity = getBasicOrderEntity();
        Order orderDto = getBasicOrderDto();
        assertThat(mapper.mapOrder(orderDto)).isEqualTo(orderEntity);
    }

    @Test
    public void testMapOrderInverse() {
        com.great.MiquelMontoro.pilotes.model.Order orderEntity = getCompleteOrderEntity() ;
        Order expectedDto = getCompleteOrderDto();

        Order mappedDto = mapper.mapOrderInverse(orderEntity);
        assertThat(mappedDto).isEqualTo(expectedDto);
    }

    @Test
    public void testMapOrdersInverse() {
        List<com.great.MiquelMontoro.pilotes.model.Order> orderEntities = List.of(getCompleteOrderEntity(), getCompleteOrderEntity());
        List<Order> expectedDtos = List.of(getCompleteOrderDto(), getCompleteOrderDto());

        List<Order> mappedDtos = mapper.mapOrdersInverse(orderEntities);
        assertThat(mappedDtos).isEqualTo(expectedDtos);
    }

    @Test
    public void testUpdateRecord() {

        Integer newNumberOfPilotes = new Random(1).nextInt();

        com.great.MiquelMontoro.pilotes.model.Order orderEntity = getBasicOrderEntity();

        Order updatedOrderDto = Order.builder()
                .deliveryAddress("a new address")
                .numberOfPilotes(newNumberOfPilotes)
                .build();

        com.great.MiquelMontoro.pilotes.model.Order updatedOrderEntity = com.great.MiquelMontoro.pilotes.model.Order.builder()
                .deliveryAddress("a new address")
                .numberOfPilotes(newNumberOfPilotes)
                .build();

        mapper.updateRecord(updatedOrderDto, orderEntity);
        assertThat(orderEntity).isEqualTo(updatedOrderEntity);
    }

    private static com.great.MiquelMontoro.pilotes.model.Order getBasicOrderEntity() {
        return com.great.MiquelMontoro.pilotes.model.Order.builder()
                .deliveryAddress("address")
                .numberOfPilotes(numberOfPilotes)
                .build();
    }

    private static com.great.MiquelMontoro.pilotes.model.Order getCompleteOrderEntity() {
        com.great.MiquelMontoro.pilotes.model.Order orderEntity = getBasicOrderEntity();
        orderEntity.setId(id);
        orderEntity.setOrderTotal(OrderConfig.SINGLE_PILOTES_PRICE*numberOfPilotes);
        orderEntity.setCreationDate(localDateTime);
        return orderEntity;
    }

    private static Order getBasicOrderDto() {
        return Order.builder()
                .deliveryAddress("address")
                .numberOfPilotes(numberOfPilotes)
                .build();
    }

    private static Order getCompleteOrderDto() {
        Order dto = getBasicOrderDto();
        dto.setId(id);
        dto.setOrderTotal(OrderConfig.SINGLE_PILOTES_PRICE*numberOfPilotes);
        dto.setOrderDate(dateString);
        return dto;
    }
}
