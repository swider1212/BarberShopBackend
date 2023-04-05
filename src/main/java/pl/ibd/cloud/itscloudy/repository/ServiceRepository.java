package pl.ibd.cloud.itscloudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.ibd.cloud.itscloudy.model.Service;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
}
