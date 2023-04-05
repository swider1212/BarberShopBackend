package pl.ibd.cloud.itscloudy.controller;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ibd.cloud.itscloudy.Consts;
import pl.ibd.cloud.itscloudy.mapper.ServiceMapper;
import pl.ibd.cloud.itscloudy.model.Service;
import pl.ibd.cloud.itscloudy.model.dto.ServiceDto;
import pl.ibd.cloud.itscloudy.service.ServiceService;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/services")
public final class ServicesController {

    @Autowired
    private final ServiceService serviceService;

    private final ServiceMapper serviceMapper = Mappers.getMapper(ServiceMapper.class);

    @GetMapping(
            value = "",
            produces = Consts.APPLICATION_JSON
    )
    public ResponseEntity<List<ServiceDto>> getAllServices(){
        List<Service> services = serviceService.getAll();

        return ResponseEntity
                .status(OK)
                .body(services.stream()
                        .map(serviceMapper::entityToDto)
                        .collect(Collectors.toList())
                );
    }

    @GetMapping(
            value = "/{serviceId}",
            produces = Consts.APPLICATION_JSON
    )
    public ResponseEntity<ServiceDto> getServiceById(@PathVariable Long serviceId){
        Service service = serviceService.getById(serviceId);

        return ResponseEntity
                .status(OK)
                .body(serviceMapper.entityToDto(service));
    }

    @PostMapping(
            value = "/create",
            consumes = Consts.APPLICATION_JSON,
            produces = Consts.APPLICATION_JSON
    )
    public ResponseEntity<ServiceDto> createService(@RequestBody ServiceDto serviceDto) {
        Service service = serviceMapper.dtoToEntity(serviceDto);
        Service createdService = serviceService.create(service);

        return ResponseEntity
                .status(OK)
                .body(serviceMapper.entityToDto(createdService));
    }

    @PutMapping(
            value = "/update/{serviceId}",
            consumes = Consts.APPLICATION_JSON,
            produces = Consts.APPLICATION_JSON
    )
    public ResponseEntity<ServiceDto> updateService(@PathVariable Long serviceId, @RequestBody ServiceDto serviceDto) {
        Service service = serviceMapper.dtoToEntity(serviceDto);
        Service updatedService = serviceService.update(serviceId, service);

        return ResponseEntity
                .status(OK)
                .body(serviceMapper.entityToDto(updatedService));
    }

    @DeleteMapping(
            value = "/delete/{serviceId}",
            produces = Consts.APPLICATION_JSON
    )
    public ResponseEntity<ServiceDto> updateService(@PathVariable Long serviceId) {
        Service deletedService = serviceService.delete(serviceId);

        return ResponseEntity
                .status(OK)
                .body(serviceMapper.entityToDto(deletedService));
    }
}
