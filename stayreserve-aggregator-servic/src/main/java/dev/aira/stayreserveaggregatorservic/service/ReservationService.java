package dev.aira.stayreserveaggregatorservic.service;

import dev.aira.stayreserve.reservation.CompleteReservationRequest;
import dev.aira.stayreserve.reservation.CreateReservationRequest;
import dev.aira.stayreserve.reservation.GetReservationRequest;
import dev.aira.stayreserve.reservation.ReservationServiceGrpc.ReservationServiceBlockingStub;
import dev.aira.stayreserveaggregatorservic.dto.CompleteReservationResponseDTO;
import dev.aira.stayreserveaggregatorservic.dto.CreateReservationResponseDTO;
import dev.aira.stayreserveaggregatorservic.dto.ReservationDTO;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    @GrpcClient("reservation")
    private ReservationServiceBlockingStub reservationServiceBlockingStub;

    public ReservationDTO getReservationById(Long reservationId) {
        var request = GetReservationRequest.newBuilder().setReservationId(reservationId).build();
        var response = reservationServiceBlockingStub.getReservation(request);
        return new ReservationDTO(response.getReservation());
    }

    public CreateReservationResponseDTO createReservation(ReservationDTO reservationDTO) {
        var request = CreateReservationRequest.newBuilder()
                .setHotelId(reservationDTO.getHotelId())
                .setUserId(reservationDTO.getUserId())
                .setStartDate(reservationDTO.getStartDate())
                .setEndDate(reservationDTO.getEndDate())
                .build();
        var response = reservationServiceBlockingStub.createReservation(request);
        return new CreateReservationResponseDTO(response.getReservationId(), response.getMessage());
    }

    public CompleteReservationResponseDTO completeReservation(Long reservationId) {
        var request = CompleteReservationRequest.newBuilder().setReservationId(reservationId).build();
        var response = reservationServiceBlockingStub.completeReservation(request);
        return new CompleteReservationResponseDTO(response.getMessage(), response.getTotalDays());
    }
}
