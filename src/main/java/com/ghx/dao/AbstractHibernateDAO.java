package com.ghx.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractHibernateDAO<T extends Serializable> {

    @Autowired
    protected SessionFactory sessionFactory;

    protected Class<T> clazz;

    protected final void setClazz(final Class<T> clazzToSet) {
        clazz = clazzToSet;
    }

    public T findById(final Long id) {
        return getCurrentSession().get(clazz, id);
    }

    public List findAll() {
        return getCurrentSession().createCriteria(clazz).list();
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
