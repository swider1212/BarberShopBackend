package pl.ibd.cloud.itscloudy.mapper;

import org.mapstruct.Mapper;
import pl.ibd.cloud.itscloudy.model.Reservation;
import pl.ibd.cloud.itscloudy.model.dto.ReservationPostDto;

@Mapper
public interface ReservationMapper {

    Reservation dtoToEntity(ReservationPostDto reservation);

    ReservationPostDto entityToDto(Reservation reservation);
}
