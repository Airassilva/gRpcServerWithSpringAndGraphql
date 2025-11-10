package dev.aira.stayreserveaggregatorservic.controller;

import dev.aira.stayreserveaggregatorservic.dto.AddHotelResponseDTO;
import dev.aira.stayreserveaggregatorservic.dto.HotelAndReservationResponseDTO;
import dev.aira.stayreserveaggregatorservic.dto.HotelDTO;
import dev.aira.stayreserveaggregatorservic.service.HotelService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class HotelController {
    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }


    @QueryMapping
    public HotelDTO getHotelById(@Argument Long id) {
        return hotelService.getHotelById(id);
    }

    @QueryMapping
    public HotelAndReservationResponseDTO getHotelAndReservationById(@Argument Long hotelId) {
        return hotelService.getHotelAndReservationById(hotelId);
    }

    @QueryMapping
    public List<HotelDTO> listAvailableHotels() {
        return hotelService.listAvailableHotels();
    }

    @MutationMapping
    public AddHotelResponseDTO addHotel (@Argument HotelDTO hotel) {
        return hotelService.addHotel(hotel);
    }
}
