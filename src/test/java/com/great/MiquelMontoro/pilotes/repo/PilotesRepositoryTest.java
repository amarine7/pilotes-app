package com.great.MiquelMontoro.pilotes.repo;

import com.great.MiquelMontoro.pilotes.dto.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource(locations = {"classpath:application.yml"})
public class PilotesRepositoryTest {

    @Autowired
    private PilotesRepository pilotesRepository;

    @Test
    void injectedRepositoryIsNotNull(){
        assertThat(pilotesRepository).isNotNull();
    }

    @Test
    @Sql(scripts={"classpath:create_records.sql"})
    void testNumberOfRecords() {
        List<com.great.MiquelMontoro.pilotes.model.Order> orders = pilotesRepository.findAll();
        assertThat(orders).isNotNull();
        assertThat(orders.size()).isEqualTo(2);
    }

    // TODO add test for specifications

    @ParameterizedTest(name = "Case: {0}")
    @MethodSource("getOrders")
    @Sql(scripts={"classpath:create_records.sql"})
    public void testGetOneRecord(String orderCase, Order order) {

        com.great.MiquelMontoro.pilotes.model.Order retrievedOrder = pilotesRepository.getOne(order.getId());

        assertThat(retrievedOrder.getOrderTotal()).isEqualTo(order.getOrderTotal());
        assertThat(retrievedOrder.getDeliveryAddress()).isEqualTo(order.getDeliveryAddress());
        assertThat(retrievedOrder.getUser().getId()).isEqualTo(order.getIdUser());
    }

    private static Stream<? extends Arguments> getOrders() {
        return Stream.of(
                Arguments.arguments("1st order", Order.builder()
                        .id(Long.valueOf(1))
                        .deliveryAddress("1234 Main Street")
                        .numberOfPilotes(5)
                        .idUser(Long.valueOf(1))
                        .build()),
                Arguments.arguments("2nd order", Order.builder()
                        .id(Long.valueOf(2))
                        .deliveryAddress("544 Younge Street")
                        .numberOfPilotes(15)
                        .idUser(Long.valueOf(2))
                        .build())
        );
    }
}
