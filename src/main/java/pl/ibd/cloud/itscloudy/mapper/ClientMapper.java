package pl.ibd.cloud.itscloudy.mapper;

import org.mapstruct.Mapper;
import pl.ibd.cloud.itscloudy.model.Client;
import pl.ibd.cloud.itscloudy.model.dto.ClientDto;

@Mapper
public interface ClientMapper {

    ClientDto entityToDto(Client client);

    Client dtoToEntity(ClientDto client);
}
