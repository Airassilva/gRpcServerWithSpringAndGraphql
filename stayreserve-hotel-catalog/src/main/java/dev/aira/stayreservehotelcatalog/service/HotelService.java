package dev.aira.stayreservehotelcatalog.service;

import dev.aira.stayreserve.catalog.*;
import dev.aira.stayreserve.catalog.CatalogServiceGrpc.CatalogServiceImplBase;
import dev.aira.stayreservehotelcatalog.service.handler.HotelCatalogRequestHandler;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class HotelService extends CatalogServiceImplBase {
    private final HotelCatalogRequestHandler hotelCatalogRequestHandler;

    public HotelService(HotelCatalogRequestHandler hotelCatalogRequestHandler) {
        this.hotelCatalogRequestHandler = hotelCatalogRequestHandler;
    }

    @Override
    public void addHotel(AddHotelRequest request, StreamObserver<AddHotelResponse> responseObserver) {
        var response = hotelCatalogRequestHandler.saveHotel(request);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void listAvailableHotel(ListAvailableHotelRequest request, StreamObserver<ListAvailableHotelResponse> responseObserver) {
        var response = hotelCatalogRequestHandler.getAllAvailableHotel();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getHotelById(GetHotelByIdRequest request, StreamObserver<GetHotelByIdResponse> responseObserver) {
        var response = hotelCatalogRequestHandler.getHotelById(request);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
