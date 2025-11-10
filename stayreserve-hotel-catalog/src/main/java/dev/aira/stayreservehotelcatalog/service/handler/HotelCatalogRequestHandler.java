package dev.aira.stayreservehotelcatalog.service.handler;

import dev.aira.stayreserve.catalog.*;
import dev.aira.stayreservehotelcatalog.exception.HotelNotFoundException;
import dev.aira.stayreservehotelcatalog.repository.HotelRepository;
import dev.aira.stayreservehotelcatalog.utils.EntityMessageMapper;
import org.springframework.stereotype.Service;

@Service
public class HotelCatalogRequestHandler {
    private final HotelRepository hotelRepository;

    public HotelCatalogRequestHandler(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public GetHotelByIdResponse getHotelById(GetHotelByIdRequest request) {
        var hotel = hotelRepository.findById(request.getId()).orElseThrow(() -> new HotelNotFoundException(request.getId()));
        return EntityMessageMapper.toMessage(hotel);
    }

    public ListAvailableHotelResponse getAllAvailableHotel() {
        var hotels = hotelRepository.findByAvailableTrue();
        return EntityMessageMapper.toListMessage(hotels);
    }

    public AddHotelResponse saveHotel(AddHotelRequest request) {
        var hotel = EntityMessageMapper.toEntity(request);
        var hotelSave = hotelRepository.save(hotel);
        return EntityMessageMapper.toResponseMessage(hotelSave);
    }

}
