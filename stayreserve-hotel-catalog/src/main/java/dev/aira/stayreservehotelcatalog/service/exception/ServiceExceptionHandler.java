package dev.aira.stayreservehotelcatalog.service.exception;

import dev.aira.stayreservehotelcatalog.exception.HotelNotFoundException;
import io.grpc.Status;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;

@GrpcAdvice
public class ServiceExceptionHandler {

    @GrpcExceptionHandler(HotelNotFoundException.class)
    public Status handlerHotelNotFound(HotelNotFoundException e){
        return Status.NOT_FOUND.withDescription(e.getMessage()).withCause(e);
    }
}
