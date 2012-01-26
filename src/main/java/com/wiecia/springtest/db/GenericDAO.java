package com.wiecia.springtest.db;

import java.util.List;

/**
 * Generic DAO interface which defines basic CRUD operations and common methods.
 */
public interface GenericDAO<E extends GenericEntity> {
	// , PK extends Serializable> {

	void setClazz(Class<E> clazz);

	E get(Long id);

	void save(E entity);

	void save(Iterable<E> entities);

	void delete(E entity);

	void delete(Iterable<E> entities);

	// List<E> find(E entity);

	List<E> findAll();

	// List<E> getAll();

}