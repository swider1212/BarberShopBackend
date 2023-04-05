package pl.ibd.cloud.itscloudy.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ibd.cloud.itscloudy.Consts;
import pl.ibd.cloud.itscloudy.mapper.ClientMapper;
import pl.ibd.cloud.itscloudy.model.Client;
import pl.ibd.cloud.itscloudy.model.dto.ClientDto;
import pl.ibd.cloud.itscloudy.service.ClientService;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
public final class ClientController {

    @Autowired
    private final ClientService clientService;

    private final ClientMapper clientMapper = Mappers.getMapper(ClientMapper.class);

    @GetMapping(
        value = "/{clientId}",
        produces = Consts.APPLICATION_JSON
    )
    public ResponseEntity<ClientDto> getClientById(@PathVariable Long clientId){
        Client client = clientService.getById(clientId);

        return ResponseEntity
                .status(OK)
                .body(clientMapper.entityToDto(client));
    }

    @GetMapping(
            value = "/emails/{clientEmail}",
            produces = Consts.APPLICATION_JSON
    )
    public ResponseEntity<ClientDto> getClientByEmail(@PathVariable String clientEmail){
        Client client = clientService.getClientByEmail(clientEmail);

        return ResponseEntity
                .status(OK)
                .body(clientMapper.entityToDto(client));
    }

    @PostMapping(
        value = "/create",
        consumes = Consts.APPLICATION_JSON,
        produces = Consts.APPLICATION_JSON
    )
    public ResponseEntity<ClientDto> createClient(@RequestBody @NonNull ClientDto clientDto){
        Client client = clientMapper.dtoToEntity(clientDto);
        Client createdClient = clientService.create(client);

        return ResponseEntity
                .status(OK)
                .body(clientMapper.entityToDto(createdClient));
    }

    @PutMapping(
            value = "/update/{clientId}",
            consumes = Consts.APPLICATION_JSON,
            produces = Consts.APPLICATION_JSON
    )
    public ResponseEntity<ClientDto> updateClient(@PathVariable Long clientId, @RequestBody ClientDto clientDto) {
        Client client = clientMapper.dtoToEntity(clientDto);
        Client updatedClient = clientService.update(clientId, client);

        return ResponseEntity
                .status(OK)
                .body(clientMapper.entityToDto(updatedClient));
    }

    @DeleteMapping(
            value = "/delete/{clientId}",
            produces = Consts.APPLICATION_JSON
    )
    public ResponseEntity<ClientDto> updateClient(@PathVariable Long clientId) {
        Client deletedClient = clientService.delete(clientId);

        return ResponseEntity
                .status(OK)
                .body(clientMapper.entityToDto(deletedClient));
    }
}
