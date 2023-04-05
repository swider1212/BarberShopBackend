package pl.ibd.cloud.itscloudy.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation implements ModelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private LocalDate day;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    @JsonManagedReference
    private Client client;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    @JsonManagedReference
    private Service service;

    @ManyToOne
    @JoinColumn(name = "barbershop_id", nullable = false)
    @JsonManagedReference
    private Barbershop barbershop;
}
