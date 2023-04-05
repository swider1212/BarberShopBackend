package pl.ibd.cloud.itscloudy.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceDto {

    private Long id;

    private String name;

    private String description;
}
