package dev.aira.stayreserveaggregatorservic.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.graphql.data.method.annotation.SchemaMapping;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SchemaMapping("HotelAndReservationResponse")
public class HotelAndReservationResponseDTO {
    private HotelDTO hotel;
    private ReservationDTO reservation;
}
