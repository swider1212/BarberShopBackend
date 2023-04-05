package pl.ibd.cloud.itscloudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.ibd.cloud.itscloudy.model.Reservation;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

//    @Query("select r from Reservation r " +
//            "join Barbershop b on b.id = r.barbershop " +
//            "join Service s on s.id = r.service " +
//            "where r.day = :day")
//    List<Reservation> findForDay(@Param("day") LocalDate day);
//
//    @Query("select r.day from Reservation r " +
//            "where r.barbershop = :barbershopId " +
//            "and r.service = :serviceId " +
//            "order by r.day desc")
//    List<LocalDate> findLastTaken(@Param("barbershopId") Long barbershopId, @Param("serviceId") Long serviceId, Pageable pageable);

    List<Reservation> findAllByBarbershopId(Long barbershopId);
}
