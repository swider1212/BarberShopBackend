package pl.ibd.cloud.itscloudy.controller;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ibd.cloud.itscloudy.Consts;
import pl.ibd.cloud.itscloudy.mapper.BarbershopMapper;
import pl.ibd.cloud.itscloudy.model.Barbershop;
import pl.ibd.cloud.itscloudy.model.dto.BarbershopDto;
import pl.ibd.cloud.itscloudy.model.dto.BarbershopPostDto;
import pl.ibd.cloud.itscloudy.service.BarbershopService;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/barbershops")
public final class BarbershopController {

    @Autowired
    private final BarbershopService barbershopService;

    private final BarbershopMapper barbershopMapper = Mappers.getMapper(BarbershopMapper.class);

    @GetMapping(
            value = "",
            produces = Consts.APPLICATION_JSON
    )
    public ResponseEntity<List<BarbershopDto>> getAllBarbershops() {
        List<Barbershop> barbershops = barbershopService.getAll();

        return ResponseEntity
                .status(OK)
                .body(barbershops.stream()
                        .map(barbershopMapper::entityToDto)
                        .collect(Collectors.toList())
                );
    }

    @GetMapping(
            value = "/{barbershopId}",
            produces = Consts.APPLICATION_JSON
    )
    public ResponseEntity<BarbershopDto> getBarbershopById(@PathVariable Long barbershopId) {
        Barbershop barbershop = barbershopService.getById(barbershopId);

        return ResponseEntity
                .status(OK)
                .body(barbershopMapper.entityToDto(barbershop));
    }

    @PostMapping(
            value = "/create",
            consumes = Consts.APPLICATION_JSON,
            produces = Consts.APPLICATION_JSON
    )
    public ResponseEntity<BarbershopDto> createBarbershop(@RequestBody BarbershopPostDto barbershopPostDto) {
        Barbershop barbershop = barbershopMapper.postDtoToEntity(barbershopPostDto);
        Barbershop createdBarbershop = barbershopService.create(barbershop);

        return ResponseEntity
                .status(OK)
                .body(barbershopMapper.entityToDto(createdBarbershop));
    }

    @PutMapping(
            value = "/update/{barbershopId}",
            consumes = Consts.APPLICATION_JSON,
            produces = Consts.APPLICATION_JSON
    )
    public ResponseEntity<BarbershopDto> updateBarbershop(@PathVariable Long barbershopId, @RequestBody BarbershopPostDto barbershopPostDto) {
        Barbershop barbershop = barbershopMapper.postDtoToEntity(barbershopPostDto);
        Barbershop updatedBarbershop = barbershopService.update(barbershopId, barbershop);

        return ResponseEntity
                .status(OK)
                .body(barbershopMapper.entityToDto(updatedBarbershop));
    }

    @DeleteMapping(
            value = "/delete/{barbershopId}",
            produces = Consts.APPLICATION_JSON
    )
    public ResponseEntity<BarbershopDto> updateBarbershop(@PathVariable Long barbershopId) {
        Barbershop deletedBarbershop = barbershopService.delete(barbershopId);

        return ResponseEntity
                .status(OK)
                .body(barbershopMapper.entityToDto(deletedBarbershop));
    }
}










