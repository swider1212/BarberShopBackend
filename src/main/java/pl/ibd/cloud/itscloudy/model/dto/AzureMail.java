package pl.ibd.cloud.itscloudy.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AzureMail {

    private String to;
    private String body;
    private String subject;
}
