package com.great.MiquelMontoro.pilotes.controllers;

import com.great.MiquelMontoro.pilotes.dto.Order;
import com.great.MiquelMontoro.pilotes.exception.MissingAcceptHeaderException;
import com.great.MiquelMontoro.pilotes.exception.MissingOrderDetailsException;
import com.great.MiquelMontoro.pilotes.service.PilotesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;



@ExtendWith({MockitoExtension.class, SpringExtension.class})
public class PilotesControllerTest {

    public static Integer numberOfPilotes = 5;
    public static final String MESSAGE_ID = "MESSAGEID";

    @Mock
    private PilotesService pilotesService;

    @Mock
    private HttpServletRequest httpServletRequest;

    @InjectMocks
    private PilotesController pilotesController;

    @Test
    public void shouldCreatePilotes() {

        Order order = getBasicOrderDto();
        when(pilotesService.createPilotes(any(), eq(order))).thenReturn(order);

        pilotesController.createPilotes("application/json", order);
        verify(pilotesService).createPilotes(any(), eq(order));
    }

    @Test
    public void shouldThrowMissingAcceptHeaderException() {
        Order order = getBasicOrderDto();
        Exception exception = assertThrows(MissingAcceptHeaderException.class,
                () -> pilotesController.createPilotes("some header", order));
    }

    private static Order getBasicOrderDto() {
        return Order.builder()
                .deliveryAddress("address")
                .numberOfPilotes(numberOfPilotes)
                .build();
    }

    @ParameterizedTest(name = "Case {0}")
    @MethodSource("getBadOrders")
    public void testMapOrderInverse(String missing, Order order) {
        assertThrows(MissingOrderDetailsException.class,
                () -> pilotesController.createPilotes("application/json", order));
    }

    private static Stream<? extends Arguments> getBadOrders() {
        return Stream.of(
                Arguments.arguments("missing address", Order.builder()
                        .deliveryAddress("")
                        .numberOfPilotes(numberOfPilotes)
                        .build()),
                Arguments.arguments("wrong number of pilotes", Order.builder()
                        .deliveryAddress("address")
                        .numberOfPilotes(3)
                        .build())
                );
    }
}
//OrderConfig.SINGLE_PILOTES_PRICE*(random.nextInt(1))),