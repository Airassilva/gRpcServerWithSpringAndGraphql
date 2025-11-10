package dev.aira.stayreserveaggregatorservic.controller;

import dev.aira.stayreserveaggregatorservic.dto.CompleteReservationResponseDTO;
import dev.aira.stayreserveaggregatorservic.dto.CreateReservationResponseDTO;
import dev.aira.stayreserveaggregatorservic.dto.ReservationDTO;
import dev.aira.stayreserveaggregatorservic.service.ReservationService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @QueryMapping
    public ReservationDTO getReservationById(@Argument Long reservationId) {
        return reservationService.getReservationById(reservationId);
    }

    @MutationMapping
    public CompleteReservationResponseDTO completeReservation(@Argument Long reservationId) {
        return reservationService.completeReservation(reservationId);
    }

    @MutationMapping
    public CreateReservationResponseDTO createReservation(@Argument ReservationDTO reservation) {
        return reservationService.createReservation(reservation);
    }
}
