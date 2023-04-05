package pl.ibd.cloud.itscloudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.ibd.cloud.itscloudy.model.Barbershop;

@Repository
public interface BarbershopRepository extends JpaRepository<Barbershop, Long> {
}
