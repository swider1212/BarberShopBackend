package pl.ibd.cloud.itscloudy.controller;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ibd.cloud.itscloudy.Consts;
import pl.ibd.cloud.itscloudy.mapper.ClientMapper;
import pl.ibd.cloud.itscloudy.model.Client;
import pl.ibd.cloud.itscloudy.model.dto.ClientDto;
import pl.ibd.cloud.itscloudy.service.ClientService;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private final ClientService clientService;

    private final ClientMapper clientMapper = Mappers.getMapper(ClientMapper.class);

    @GetMapping(
            value = "",
            produces = Consts.APPLICATION_JSON
    )
    public ResponseEntity<ClientDto> getClientByPrincipal(OAuth2AuthenticationToken oAuth2AuthenticationToken) {
            Client client = clientService.getClientByPrincipal(oAuth2AuthenticationToken);

        return ResponseEntity
                .status(OK)
                .body(clientMapper.entityToDto(client));
    }
}