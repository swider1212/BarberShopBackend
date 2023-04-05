package pl.ibd.cloud.itscloudy.mapper;

import org.mapstruct.Mapper;
import pl.ibd.cloud.itscloudy.model.Service;
import pl.ibd.cloud.itscloudy.model.dto.ServiceDto;

@Mapper
public interface ServiceMapper {

    Service dtoToEntity(ServiceDto service);

    ServiceDto entityToDto(Service service);
}
