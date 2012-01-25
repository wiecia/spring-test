package com.wiecia.springtest.db.hibernate;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.wiecia.springtest.db.GenericDAO;
import com.wiecia.springtest.db.GenericEntity;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class GenericHibernateDAO<E extends GenericEntity> extends
		AbstractHibernateDAO<E> implements GenericDAO<E> {

}
