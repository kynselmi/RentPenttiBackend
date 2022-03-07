package com.kingofnone.rentpentti.dao.impl;

import com.kingofnone.rentpentti.dao.Dao;
import com.kingofnone.rentpentti.model.BaseEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T extends BaseEntity> implements Dao<T> {

    private static final Logger logger = LogManager.getLogger(AbstractDao.class);

    private final SessionFactory sessionFactory;

    private Class<T> clazz;

    public AbstractDao() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    protected Session openSession() {
        return sessionFactory.openSession();
    }

    @Override
    public List<T> getAll() {
        Session session = openSession();
        session.beginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
        TypedQuery<T> allQuery = session.createQuery(criteriaQuery.select(criteriaQuery.from(clazz)));
        List<T> results = allQuery.getResultList();
        session.close();
        return results;
    }

    @Override
    public Optional<T> get(long id) {
        return findByAttribute("id", id);
    }

    @Override
    public Optional<T> create(final T entity) {
        Session session = openSession();
        try {
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
            session.close();
        } catch (ConstraintViolationException e) {
            logger.error("Failed to create entity because entity with unique values was already found in database");
            return Optional.empty();
        } finally {
            session.close();
        }
        return Optional.of(entity);
    }

    @Override
    public Optional<T> update(final T entity) {
        Session session = openSession();
        session.beginTransaction();
        T updatedEntity = (T) session.merge(entity);
        session.getTransaction().commit();
        session.close();
        return Optional.of(updatedEntity);
    }

    @Override
    public void delete(final T entity) {
        Session session = openSession();
        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Optional<T> findByAttribute(final String attribute, final Object value) {
        Session session = openSession();
        session.beginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
        Root<T> rootQuery = criteriaQuery.from(clazz);
        Predicate idEquals = criteriaBuilder.equal(rootQuery.get(attribute), value);
        TypedQuery<T> getQuery = session.createQuery(criteriaQuery.select(rootQuery).where(idEquals));
        T result = getQuery.getSingleResult();

        return Optional.ofNullable(result);
    }

    @Override
    public void deleteById(final long id) {
        get(id).ifPresent(this::delete);
    }

    @Override
    public void safeDelete(final T entity) {
        entity.setDeleted(true);
        update(entity);
    }

    public void setClazz(Class< T > clazzToSet){
        this.clazz = clazzToSet;
    }
}
