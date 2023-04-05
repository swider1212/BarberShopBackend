package pl.ibd.cloud.itscloudy.service;

import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.ibd.cloud.itscloudy.exception.ConflictException;
import pl.ibd.cloud.itscloudy.exception.EntityNotFoundException;
import pl.ibd.cloud.itscloudy.mapper.ReservationMapper;
import pl.ibd.cloud.itscloudy.model.Barbershop;
import pl.ibd.cloud.itscloudy.model.Client;
import pl.ibd.cloud.itscloudy.model.Reservation;
import pl.ibd.cloud.itscloudy.model.Service;
import pl.ibd.cloud.itscloudy.model.dto.AzureMail;
import pl.ibd.cloud.itscloudy.model.dto.ReservationPostDto;
import pl.ibd.cloud.itscloudy.repository.BarbershopRepository;
import pl.ibd.cloud.itscloudy.repository.ClientRepository;
import pl.ibd.cloud.itscloudy.repository.ReservationRepository;
import pl.ibd.cloud.itscloudy.repository.ServiceRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

@Slf4j
@org.springframework.stereotype.Service
public class ReservationsService extends CrudService<Reservation> {

    private static final String MAIL_URL = "https://prod-26.westeurope.logic.azure.com/workflows/5061c90ad76744eea3264a857a1100e7/triggers/manual/paths/invoke";
    private static final String SUBJECT = "Reservation confirmation";
    private static final String BODY = """
                <h2>Hi %s %s</h2><br>
                            
                You reservation has been accepted! Below you can find reservation details.<br>
                        
                <h3><b>%s</b></h3>
                %s %s<br>
                %s %s<br>
                %s<br>
                
                <h3><b>%s</b></h3>
                
                <h3>%s</h3>
                
                Hope you enjoy our services!
                
                Sincerely<br>
                Insane Scissors
                """;
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private BarbershopRepository barbershopRepository;

    private RestTemplate mailRestTemplate = new RestTemplate();

    private final ReservationMapper reservationMapper = Mappers.getMapper(ReservationMapper.class);

    private final Comparator<Reservation> comparator = Comparator.comparing(Reservation::getDay);

    public ReservationsService(JpaRepository<Reservation, Long> entityRepository) {
        super(entityRepository);
    }

    @Override
    protected Class<Reservation> getType() {
        return Reservation.class;
    }

    public Reservation createReservation(ReservationPostDto reservationPostDto) {

        Service service = serviceRepository.findById(reservationPostDto.getServiceId())
                .orElseThrow(() -> new EntityNotFoundException(Service.class, "id", reservationPostDto.getServiceId()));

        Barbershop barbershop = barbershopRepository.findById(reservationPostDto.getBarbershopId())
                .orElseThrow(() -> new EntityNotFoundException(Barbershop.class, "id", reservationPostDto.getBarbershopId()));

        Client client = clientRepository.findById(reservationPostDto.getClientId())
                .orElseThrow(() -> new EntityNotFoundException(Client.class, "id", reservationPostDto.getClientId()));

        List<Reservation> barbershopReservations = reservationRepository.findAllByBarbershopId(reservationPostDto.getBarbershopId());

        boolean taken = barbershopReservations.stream()
                .anyMatch(r -> r.getDay().equals(reservationPostDto.getDay()));

        if (taken)
            throw new ConflictException("Barbershop you chose is unfortunately not available!");

        Reservation saveReservation = reservationMapper.dtoToEntity(reservationPostDto);
        saveReservation.setService(service);
        saveReservation.setBarbershop(barbershop);
        saveReservation.setClient(client);

        Reservation result = reservationRepository.saveAndFlush(saveReservation);

        AzureMail mail = AzureMail.builder()
                .to(client.getEmail())
                .subject(SUBJECT)
                .body(createMailBody(client, barbershop, service, reservationPostDto.getDay()))
                .build();

        String url = UriComponentsBuilder.fromHttpUrl(MAIL_URL)
                .queryParam("api-version", "2016-10-01")
                .queryParam("sp", "/triggers/manual/run")
                .queryParam("sv", "1.0")
                .queryParam("sig", "Yj8gjQCBUZd7u5owdtNnTzwnf-rR2QjWmGlOa3lE9Fg")
                .encode().toUriString();

        mailRestTemplate.postForLocation(url, mail);

        return result;
    }

    public List<LocalDate> findSignificantReservationsDates(Long barbershopId) {
        LocalDate now = LocalDate.now();
        List<Reservation> reservations = reservationRepository.findAll().stream()
                .filter(r -> r.getBarbershop().getId().equals(barbershopId))
                .filter(r -> r.getDay().isAfter(now))
                .sorted(comparator)
                .toList();

        return reservations.stream()
                .map(Reservation::getDay)
                .toList();
    }

    private static String createMailBody(Client client, Barbershop barbershop, Service service, LocalDate date){
        return String.format(BODY,
                client.getFirstName(), client.getLastName(),
                barbershop.getName(),
                barbershop.getStreet(), barbershop.getNumber(),
                barbershop.getPostalCode(), barbershop.getCity(),
                barbershop.getCountry(),
                service.getName(),
                date.format(dateFormat));
    }
}
