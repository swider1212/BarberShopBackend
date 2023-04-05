package pl.ibd.cloud.itscloudy.mapper;

import org.mapstruct.Mapper;
import pl.ibd.cloud.itscloudy.model.Barbershop;
import pl.ibd.cloud.itscloudy.model.Service;
import pl.ibd.cloud.itscloudy.model.dto.BarbershopDto;
import pl.ibd.cloud.itscloudy.model.dto.BarbershopPostDto;

@Mapper
public interface BarbershopMapper {

    Barbershop dtoToEntity(BarbershopDto barbershop);

    BarbershopDto entityToDto(Barbershop barbershop);

    BarbershopDto postDtoToDto(BarbershopPostDto barbershopPostDto);

    default Barbershop postDtoToEntity(BarbershopPostDto barbershopPostDto) {
        BarbershopDto barbershopDto = postDtoToDto(barbershopPostDto);
        Barbershop barbershop = dtoToEntity(barbershopDto);

        barbershop.setServices(barbershopPostDto.getServiceIds().stream()
                .map(id -> Service.builder()
                        .id(id)
                        .build())
                .toList());

        return barbershop;
    }
}
