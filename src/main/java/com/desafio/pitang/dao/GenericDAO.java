package com.desafio.pitang.dao;

import java.util.List;

import org.hibernate.Session;

public interface GenericDAO<Tipo> {

	Session getSession();

	void save(Tipo entity);

	void update(Tipo entity);

	void delete(Tipo entity);

	List<Tipo> findAll() throws Exception;

	Tipo findById(long id);

	void close();

}