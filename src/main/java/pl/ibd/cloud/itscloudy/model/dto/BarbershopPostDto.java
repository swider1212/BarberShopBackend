package pl.ibd.cloud.itscloudy.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BarbershopPostDto {

    private String name;

    private String description;

    private String country;

    private String city;

    @JsonProperty("postal_code")
    private String postalCode;

    private String street;

    private String number;

    private double longitude;

    private double latitude;

    @JsonProperty("service_ids")
    private List<Long> serviceIds;
}
