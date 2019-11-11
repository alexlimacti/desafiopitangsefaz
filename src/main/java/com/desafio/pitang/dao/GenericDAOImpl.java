package com.desafio.pitang.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class GenericDAOImpl<Tipo> implements GenericDAO<Tipo> {

	private final Session session;
    private final Class<Tipo> classePersistente;
 
    public GenericDAOImpl(Class<Tipo> tipo) {
        this.session = (Session) HibernateUtil.getSessionFactory().openSession();
        this.classePersistente = tipo;
    }
 
    public Session getSession() {
        return session;
    }
 
    public void save(Tipo entity) {
        try {
            getSession().getTransaction().begin();
            getSession().save(entity);
            getSession().getTransaction().commit();
        } catch (Throwable t) {
            getSession().getTransaction().rollback();
            t.printStackTrace();
        } finally {
            close();
        }
    }
 
    public void update(Tipo entity) {
        try {
            getSession().getTransaction().begin();
            getSession().merge(entity);
            getSession().getTransaction().commit();
        } catch (Throwable t) {
            getSession().getTransaction().rollback();
            t.printStackTrace();
        } finally {
            close();
        }
    }
 
    public void delete(Tipo entity) {
        try {
            getSession().getTransaction().begin();
            getSession().delete(entity);
            getSession().getTransaction().commit();
        } catch (Throwable t) {
            getSession().getTransaction().rollback();
            t.printStackTrace();
        } finally {
            close();
        }
    }
 
    public List<Tipo> findAll() throws Exception {
        return getSession().createCriteria(classePersistente).list();
    }
 
    public Tipo findById(long id) {
        return (Tipo) getSession().createCriteria(classePersistente).add(Restrictions.eq("id", id)).uniqueResult();
    }
 
    public void close() {
        if (getSession() != null && getSession().isOpen()) {
            getSession().close();
        }
    }
}
