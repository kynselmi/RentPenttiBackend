package com.kingofnone.rentpentti.service;

import com.kingofnone.rentpentti.model.BaseEntity;

import java.util.List;
import java.util.Optional;

public interface Service<T extends BaseEntity> {
    List<T> findAll();

    List<T> findAllActive();

    T create(T entity);

    Optional<T> getById(Long id);

    T updateById(Long id, T entity);

    void deleteById(Long id);
}
