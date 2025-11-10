package dev.aira.stayreservehotelcatalog.utils;

import dev.aira.stayreserve.catalog.*;
import dev.aira.stayreservehotelcatalog.entity.Hotel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EntityMessageMapper {

    public static Hotel toEntity (AddHotelRequest request){
        return new Hotel(
                request.getHotel().getName(),
                request.getHotel().getDescription(),
                request.getHotel().getCity(),
                request.getHotel().getState(),
                request.getHotel().getCountry(),
                request.getHotel().getStars(),
                request.getHotel().getPricePerNight(),
                request.getHotel().getAvailable()
        );
    }

    public static GetHotelByIdResponse toMessage (Hotel hotel){
        return GetHotelByIdResponse.newBuilder().setHotel(
                HotelProto.newBuilder()
                        .setId(hotel.getId())
                        .setName(hotel.getName())
                        .setDescription(hotel.getDescription())
                        .setCity(hotel.getCity())
                        .setState(hotel.getState())
                        .setCountry(hotel.getCountry())
                        .setStars(hotel.getStarts())
                        .setPricePerNight(hotel.getPricePerNight())
                        .setAvailable(hotel.isAvailable())
                        .build()
        ).build();
    }

    public static ListAvailableHotelResponse toListMessage (List<Hotel> hotels){
        var hotelProto = hotels.stream().map(
                hotel -> HotelProto.newBuilder()
                        .setId(hotel.getId())
                        .setName(hotel.getName())
                        .setDescription(hotel.getDescription())
                        .setCity(hotel.getCity())
                        .setState(hotel.getState())
                        .setCountry(hotel.getCountry())
                        .setStars(hotel.getStarts())
                        .setPricePerNight(hotel.getPricePerNight())
                        .setAvailable(hotel.isAvailable())
                        .build()
        ).toList();
        return ListAvailableHotelResponse.newBuilder().addAllHotels(hotelProto).build();
    }

    public static AddHotelResponse toResponseMessage(Hotel hotel){
        return AddHotelResponse.newBuilder()
                .setHotelId(hotel.getId())
                .setMessage("Hotel added successfully")
                .build();
    }
}
