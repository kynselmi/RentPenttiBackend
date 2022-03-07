package com.kingofnone.rentpentti.service.model.impl;

import com.kingofnone.rentpentti.dao.Dao;
import com.kingofnone.rentpentti.model.BaseEntity;
import com.kingofnone.rentpentti.service.model.ModelService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AbstractModelService<T extends BaseEntity> implements ModelService<T> {

    protected Dao dao = null;

    @Override
    public List<T> findAll() {
        return dao.getAll();
    }

    @Override
    public List<T> findAllActive() {
        List<T> entities = dao.getAll();
        entities = entities.stream().filter(BaseEntity::isNotDeleted).collect(Collectors.toList());
        return entities;
    }

    @Override
    public Optional<T> create(T entity) {
        dao.create(entity);
        return Optional.of(entity);
    }

    @Override
    public Optional<T> getById(Long id) {
        return dao.get(id);
    }

    @Override
    public Optional<T> update(T entity) {
        return dao.update(entity);
    }

    @Override
    public Optional<T> updateById(Long id, T entity) {
        entity.setId(id);
        return dao.update(entity);
    }

    @Override
    public void deleteById(Long id) {
        dao.deleteById(id);
    }

    public void setDao(Dao dao) {
        this.dao = dao;
    }
}

