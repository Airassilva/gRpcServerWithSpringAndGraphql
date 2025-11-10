package dev.aira.stayreservehotelcatalog.exception;

public class HotelNotFoundException extends RuntimeException {
  private static final String MESSAGE = "Hotel [id=%d] is not found";
    public HotelNotFoundException(Long id) {
      super(MESSAGE.formatted(id));
    }
}
