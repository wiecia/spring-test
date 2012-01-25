package com.wiecia.springtest.db.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.base.Preconditions;
import com.wiecia.springtest.db.GenericDAO;
import com.wiecia.springtest.db.GenericEntity;
import com.wiecia.springtest.db.SoftDeleteable;

public abstract class AbstractHibernateDAO<E extends GenericEntity> implements
		GenericDAO<E> {

	@Autowired
	SessionFactory sessionFactory;

	private Class<E> clazz;

	public void setClazz(final Class<E> clazz) {
		this.clazz = clazz;
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public E get(Long id) {
		Preconditions.checkNotNull(id);
		return (E) getSession().get(clazz, id);
	}

	@Override
	public void save(final E entity) {
		Preconditions.checkNotNull(entity);
		getSession().saveOrUpdate(entity);

	}

	@Override
	public void save(final Iterable<E> entities) {
		for (E entity : entities) {
			save(entity);
		}

	}

	@Override
	public void delete(final E entity) {
		if (entity instanceof SoftDeleteable) {
			((SoftDeleteable) entity).setDeletedDate(new LocalDate().toDate());
		} else {
			getSession().delete(entity);
		}
	}

	@Override
	public void delete(final Iterable<E> entities) {
		for (E entity : entities) {
			delete(entity);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<E> findAll() {
		return getSession().createQuery("from " + clazz.getName()).list();
	}

}
