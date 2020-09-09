package com.kingofnone.rentpentti.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface Dao<T extends Serializable> {
    List getAll();

    Optional get(long id);

    T create(T entity);

    T update(T entity);

    void delete(T entity);

    void deleteById(long id);

    void safeDelete(T entity);
}
