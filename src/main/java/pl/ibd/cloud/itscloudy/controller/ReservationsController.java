package pl.ibd.cloud.itscloudy.controller;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ibd.cloud.itscloudy.Consts;
import pl.ibd.cloud.itscloudy.mapper.ReservationMapper;
import pl.ibd.cloud.itscloudy.model.Reservation;
import pl.ibd.cloud.itscloudy.model.dto.ReservationPostDto;
import pl.ibd.cloud.itscloudy.service.ReservationsService;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/reservations")
public final class ReservationsController {

    @Autowired
    private ReservationsService reservationsService;

    private final ReservationMapper reservationMapper = Mappers.getMapper(ReservationMapper.class);

    @GetMapping(
            value = "",
            produces = Consts.APPLICATION_JSON
    )
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> barbershops = reservationsService.getAll();

        return ResponseEntity
                .status(OK)
                .body(barbershops);
    }

    @PostMapping(
            value = "/create",
            consumes = Consts.APPLICATION_JSON,
            produces = Consts.APPLICATION_JSON
    )
    public ResponseEntity<ReservationPostDto> createReservation(@RequestBody ReservationPostDto reservationPostDto) {
        Reservation reservation = reservationsService.createReservation(reservationPostDto);

        return ResponseEntity
                .status(OK)
                .body(reservationMapper.entityToDto(reservation));
    }

    @GetMapping(
            value = "/significant-reservations-dates",
            produces = Consts.APPLICATION_JSON
    )
    public ResponseEntity<List<LocalDate>> getSignificantReservations(@RequestParam("barbershopId") Long barbershopId) {
        List<LocalDate> significantReservationsDates = reservationsService.findSignificantReservationsDates(barbershopId);

        return ResponseEntity
                .status(OK)
                .body(significantReservationsDates);
    }
}
