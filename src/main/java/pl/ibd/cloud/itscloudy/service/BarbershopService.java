package pl.ibd.cloud.itscloudy.service;

import org.springframework.stereotype.Service;
import pl.ibd.cloud.itscloudy.model.Barbershop;
import pl.ibd.cloud.itscloudy.repository.BarbershopRepository;


@Service
public final class BarbershopService extends CrudService<Barbershop> {

    public BarbershopService(BarbershopRepository barbershopRepository) {
        super(barbershopRepository);
    }

    @Override
    protected Class<Barbershop> getType() {
        return Barbershop.class;
    }
}
