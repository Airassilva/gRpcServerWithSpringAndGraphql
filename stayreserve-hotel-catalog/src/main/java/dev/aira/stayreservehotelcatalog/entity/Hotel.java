package dev.aira.stayreservehotelcatalog.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String city;
    private String state;
    private String country;
    private int starts;
    private double pricePerNight;
    private boolean available;

    public Hotel(String name, String description, String city, String state, String country, int starts, double pricePerNight, boolean available) {
        this.name = name;
        this.description = description;
        this.city = city;
        this.state = state;
        this.country = country;
        this.starts = starts;
        this.pricePerNight = pricePerNight;
        this.available = available;
    }
}
