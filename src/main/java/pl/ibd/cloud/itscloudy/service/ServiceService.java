package pl.ibd.cloud.itscloudy.service;

import pl.ibd.cloud.itscloudy.model.Service;
import pl.ibd.cloud.itscloudy.repository.ServiceRepository;

@org.springframework.stereotype.Service
public final class ServiceService extends CrudService<Service> {

    public ServiceService(ServiceRepository serviceRepository) {
        super(serviceRepository);
    }

    @Override
    protected Class<Service> getType() {
        return Service.class;
    }

}
