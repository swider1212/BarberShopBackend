package pl.ibd.cloud.itscloudy.service;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ibd.cloud.itscloudy.exception.EntityNotFoundException;
import pl.ibd.cloud.itscloudy.model.ModelEntity;

import java.util.List;
import java.util.function.Consumer;

public abstract class CrudService<TEntity extends ModelEntity> {

    private final Class<TEntity> type = getType();

    protected final JpaRepository<TEntity, Long> entityRepository;

    protected CrudService(JpaRepository<TEntity, Long> entityRepository) {
        this.entityRepository = entityRepository;
    }

    public List<TEntity> getAll() {

        return entityRepository
                .findAll();
    }

    public TEntity getById(Long id) {

        return entityRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(type, "id", id));
    }

    public TEntity create(TEntity entity) {

        return entityRepository
                .save(entity);
    }

    public TEntity update(Long id, TEntity entity) {
        entity.setId(id);

        return entityRepository
                .save(entity);
    }

    public TEntity update(Long id, Consumer<TEntity> modExpr) {
        TEntity entity = getById(id);

        modExpr.accept(entity);

        return entityRepository
                .save(entity);
    }

    public TEntity delete(Long id) {
        TEntity entity = getById(id);

        entityRepository.delete(entity);

        return entity;
    }

    abstract protected Class<TEntity> getType();
}
