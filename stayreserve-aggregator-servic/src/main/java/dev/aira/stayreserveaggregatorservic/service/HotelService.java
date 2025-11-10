package dev.aira.stayreserveaggregatorservic.service;

import dev.aira.stayreserve.catalog.*;
import dev.aira.stayreserve.catalog.CatalogServiceGrpc.CatalogServiceBlockingStub;
import dev.aira.stayreserve.reservation.GetReservationByHotelIdRequest;
import dev.aira.stayreserve.reservation.ReservationServiceGrpc.ReservationServiceBlockingStub;
import dev.aira.stayreserveaggregatorservic.dto.AddHotelResponseDTO;
import dev.aira.stayreserveaggregatorservic.dto.HotelAndReservationResponseDTO;
import dev.aira.stayreserveaggregatorservic.dto.HotelDTO;
import dev.aira.stayreserveaggregatorservic.dto.ReservationDTO;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    @GrpcClient("hotel-catalog")
    private CatalogServiceBlockingStub catalogServiceBlockingStub;

    @GrpcClient("reservation")
    private ReservationServiceBlockingStub reservationServiceBlockingStub;

    public HotelDTO getHotelById(Long hotelId) {
        var request = GetHotelByIdRequest.newBuilder().setId(hotelId).build();
        var response = catalogServiceBlockingStub.getHotelById(request);
        return new HotelDTO(response.getHotel());
    }

    public List<HotelDTO> listAvailableHotels() {
        var request = ListAvailableHotelRequest.newBuilder().build();
        var response = catalogServiceBlockingStub.listAvailableHotel(request);
        return HotelDTO.fromProtoList(response.getHotelsList());
    }

    public AddHotelResponseDTO addHotel(HotelDTO hotelDTO) {
        HotelProto hotelProto = HotelProto.newBuilder()
                .setName(hotelDTO.getName())
                .setCity(hotelDTO.getCity())
                .setCountry(hotelDTO.getCountry())
                .setState(hotelDTO.getState())
                .setDescription(hotelDTO.getDescription())
                .setPricePerNight(hotelDTO.getPricePerNight())
                .setStars(hotelDTO.getStars())
                .setAvailable(hotelDTO.getAvailable())
                .build();
        var request = AddHotelRequest.newBuilder().setHotel(hotelProto).build();
        var response = catalogServiceBlockingStub.addHotel(request);
        return new AddHotelResponseDTO(response.getHotelId(), response.getMessage());
    }

    public HotelAndReservationResponseDTO getHotelAndReservationById(Long hotelId) {
        var hotelRequest = GetHotelByIdRequest.newBuilder().setId(hotelId).build();
        var hotelResponse = catalogServiceBlockingStub.getHotelById(hotelRequest);
        var hotelDTO = new HotelDTO(hotelResponse.getHotel());

        var reservationRequest = GetReservationByHotelIdRequest.newBuilder().setHotelId(hotelId).build();
        var reservationResponse = reservationServiceBlockingStub.getReservationByHotelId(reservationRequest);
        var reservationDTO = new ReservationDTO(reservationResponse.getReservation());

        return new HotelAndReservationResponseDTO(hotelDTO, reservationDTO);
    }
}
