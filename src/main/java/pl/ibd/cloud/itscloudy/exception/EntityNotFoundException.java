package pl.ibd.cloud.itscloudy.exception;

import lombok.Getter;
import pl.ibd.cloud.itscloudy.model.ModelEntity;

@Getter
public class EntityNotFoundException extends InsaneScissorsException {

    private static final String messageTemplate = "%s entity with %s = %s was not found!";

    private final Class entityClass;

    public <T extends ModelEntity> EntityNotFoundException(Class<T> entityClass, String paramName, Object paramValue) {
        super(String.format(messageTemplate, entityClass, paramName, paramValue));

        this.entityClass = entityClass;
    }
}

