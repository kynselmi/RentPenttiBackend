package com.kingofnone.rentpentti.service.impl;

import com.kingofnone.rentpentti.dao.Dao;
import com.kingofnone.rentpentti.model.BaseEntity;
import com.kingofnone.rentpentti.service.Service;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AbstractService<T extends BaseEntity> implements Service<T> {

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
    public T create(T entity) {
        dao.create(entity);
        return entity;
    }

    @Override
    public Optional<T> getById(Long id) {
        return dao.get(id);
    }

    @Override
    public T updateById(Long id, T entity) {
        entity.setId(id);
        return (T) dao.update(entity);
    }

    @Override
    public void deleteById(Long id) {
        dao.deleteById(id);
    }

    public void setDao(Dao dao) {
        this.dao = dao;
    }
}

