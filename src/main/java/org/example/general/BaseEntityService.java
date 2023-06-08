package org.example.general;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Getter
public abstract class BaseEntityService<ENTITY extends BaseEntity, REPOSITORY extends JpaRepository<ENTITY, Long>> {

    private final REPOSITORY repository;

    public ENTITY save(ENTITY entity) {

        BaseAdditionalFields baseAdditionalFields = entity.getBaseAdditionalFields();
        if (baseAdditionalFields == null) {
            baseAdditionalFields = new BaseAdditionalFields();
        }

        if (entity.getId() == null) {
            baseAdditionalFields.setCreateDate(LocalDateTime.now());
        }

        baseAdditionalFields.setUpdateDate(LocalDateTime.now());

        entity.setBaseAdditionalFields(baseAdditionalFields);
        entity = repository.save(entity);

        return entity;
    }

    public List<ENTITY> findAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void delete(ENTITY entity) {
        repository.delete(entity);
    }

    public Optional<ENTITY> findById(Long id) {
        return repository.findById(id);
    }

    public ENTITY findByIdWithControl(Long id) {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("Entity not found with id: " + id));
    }

    public boolean isExist(Long id) {
        return repository.existsById(id);
    }
}
