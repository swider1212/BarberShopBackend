package pl.ibd.cloud.itscloudy.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Barbershop implements ModelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String description;

    private String country;

    private String city;

    private String postalCode;

    private String street;

    private String number;

    private double longitude;

    private double latitude;

    @ManyToMany
    @JsonBackReference
    @JoinTable(
            name = "barbershop_service",
            joinColumns = @JoinColumn(name = "barbershop_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private List<Service> services;
}
