package dev.aira.stayreserveaggregatorservic.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.graphql.data.method.annotation.SchemaMapping;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SchemaMapping("CompleteReservationResponse")
public class CompleteReservationResponseDTO {
    private String message;
    private Long totalDays;
}
