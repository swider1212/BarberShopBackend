package pl.ibd.cloud.itscloudy.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationPostDto {

    private LocalDate day;

    @JsonProperty("client_id")
    private Long clientId;

    @JsonProperty("service_id")
    private Long serviceId;

    @JsonProperty("barbershop_id")
    private Long barbershopId;
}
