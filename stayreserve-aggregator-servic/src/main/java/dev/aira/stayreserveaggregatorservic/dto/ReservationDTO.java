package dev.aira.stayreserveaggregatorservic.dto;

import dev.aira.stayreserve.reservation.ReservationProto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.graphql.data.method.annotation.SchemaMapping;

@NoArgsConstructor
@Getter
@Setter
@SchemaMapping("Reservation")
public class ReservationDTO {
    private long id;
    private long hotelId;
    private long userId;
    private String startDate;
    private String endDate;
    private String status;

    public ReservationDTO(ReservationProto reservation) {
        this.id = reservation.getReservationId();
        this.hotelId = reservation.getHotelId();
        this.userId = reservation.getUserId();
        this.startDate = reservation.getStartDate();
        this.endDate = reservation.getEndDate();
        this.status = reservation.getStatus().name();
    }
}
