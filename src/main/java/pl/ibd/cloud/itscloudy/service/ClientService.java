package pl.ibd.cloud.itscloudy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;
import pl.ibd.cloud.itscloudy.exception.EntityNotFoundException;
import pl.ibd.cloud.itscloudy.model.Client;
import pl.ibd.cloud.itscloudy.repository.ClientRepository;

@Service
public final class ClientService extends CrudService<Client> {

    @Autowired
    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        super(clientRepository);
    }

    @Override
    protected Class<Client> getType() {
        return Client.class;
    }

    public Client getClientByEmail(String email) {
        return clientRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(Client.class, "email", email));
    }

    public Client getClientByPrincipal(OAuth2AuthenticationToken oAuth2AuthenticationToken) {
        String clientEmail = oAuth2AuthenticationToken.getPrincipal().getAttribute("email");
        return clientRepository.findByEmail(clientEmail).orElseThrow(() -> new EntityNotFoundException(Client.class, "email", clientEmail));
    }
}
