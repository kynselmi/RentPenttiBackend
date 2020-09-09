package com.kingofnone.rentpentti.dao.impl;

import com.kingofnone.rentpentti.dao.Dao;
import com.kingofnone.rentpentti.model.BaseEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T extends BaseEntity> implements Dao<T> {

    private SessionFactory sessionFactory;

    private Class<T> clazz;

    public AbstractDao() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    private Session openSession() {
        return sessionFactory.openSession();
    }

    @Override
    public List getAll() {
        Session session = openSession();
        session.beginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
        TypedQuery<T> allQuery = session.createQuery(criteriaQuery.select(criteriaQuery.from(clazz)));
        List results = allQuery.getResultList();
        session.close();
        return results;
    }

    @Override
    public Optional get(long id) {
        Session session = openSession();
        Optional resultOptional = Optional.empty();
        try {
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
            Root<T> rootQuery = criteriaQuery.from(clazz);
            Predicate idEquals = criteriaBuilder.equal(rootQuery.get("id"), id);
            TypedQuery<T> getQuery = session.createQuery(criteriaQuery.select(rootQuery).where(idEquals));
            T result = getQuery.getSingleResult();
            resultOptional = Optional.ofNullable(result);
        } catch (NoResultException e) {

        } finally {
            session.close();
        }
        return resultOptional;
    }

    @Override
    public T create(final T entity) {
        Session session = openSession();
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
        session.close();
        return entity;
    }

    @Override
    public T update(final T entity) {
        Session session = openSession();
        session.beginTransaction();
        session.merge(entity);
        session.getTransaction().commit();
        session.close();
        return entity;
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
    public void deleteById(final long id) {
        get(id).ifPresent(entity -> delete((T)entity));
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
