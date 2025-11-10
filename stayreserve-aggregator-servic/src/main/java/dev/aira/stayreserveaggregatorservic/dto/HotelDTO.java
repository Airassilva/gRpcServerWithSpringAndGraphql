package dev.aira.stayreserveaggregatorservic.dto;

import dev.aira.stayreserve.catalog.HotelProto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.graphql.data.method.annotation.SchemaMapping;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SchemaMapping("Hotel")
public class HotelDTO {
    private long id;
    private String name;
    private String description;
    private String city;
    private String country;
    private String state;
    private int stars;
    private double pricePerNight;
    private Boolean available;

    public HotelDTO(HotelProto hotel) {
        this.id = hotel.getId();
        this.name = hotel.getName();
        this.description = hotel.getDescription();
        this.city = hotel.getCity();
        this.country = hotel.getCountry();
        this.state = hotel.getState();
        this.stars = hotel.getStars();
        this.pricePerNight = hotel.getPricePerNight();
        this.available = hotel.getAvailable();
    }

    public static List<HotelDTO> fromProtoList(List<HotelProto> hotelProtos) {
        return hotelProtos.stream()
                .map(HotelDTO::new)
                .toList();
    }

}
