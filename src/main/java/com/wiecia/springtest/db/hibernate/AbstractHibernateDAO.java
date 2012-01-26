package com.wiecia.springtest.db.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.base.Preconditions;
import com.wiecia.springtest.db.GenericEntity;
import com.wiecia.springtest.db.SoftDeleteable;

public abstract class AbstractHibernateDAO<E extends GenericEntity> {
	// implements GenericDAO<E> {

	private static final Logger LOG = LoggerFactory
			.getLogger(AbstractHibernateDAO.class);

	@Autowired
	SessionFactory sessionFactory;

	private Class<E> clazz;

	public void setClazz(final Class<E> clazz) {
		this.clazz = clazz;
	}

	// @SuppressWarnings("unchecked")
	// public AbstractHibernateDAO() {
	// Type[] types = ((ParameterizedType) getClass().getGenericSuperclass()
	// .getClass().getGenericSuperclass()).getActualTypeArguments();
	// if (types[0] instanceof ParameterizedType) {
	// ParameterizedType type = (ParameterizedType) types[0];
	// clazz = (Class<E>) type.getRawType();
	// } else {
	// clazz = (Class<E>) types[0];
	// }
	// }

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public E get(Long id) {
		Preconditions.checkNotNull(id);
		return (E) getSession().get(clazz, id);
	}

	public void save(final E entity) {
		Preconditions.checkNotNull(entity);
		getSession().saveOrUpdate(entity);

	}

	public void save(final Iterable<E> entities) {
		for (E entity : entities) {
			save(entity);
		}

	}

	public void delete(final E entity) {
		if (entity instanceof SoftDeleteable) {
			((SoftDeleteable) entity).setDeletedDate(new LocalDate().toDate());
			LOG.debug("Soft deleted entity: " + entity);
		} else {
			getSession().delete(entity);
		}
	}

	public void delete(final Iterable<E> entities) {
		for (E entity : entities) {
			delete(entity);
		}
	}

	@SuppressWarnings("unchecked")
	public List<E> findAll() {
		// return getSession().createQuery("from " + clazz.getName()).list();
		Criteria crit = sessionFactory.getCurrentSession()
				.createCriteria(clazz);
		return crit.list();
	}
}
