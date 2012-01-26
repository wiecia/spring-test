package com.wiecia.springtest.db.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.wiecia.springtest.db.hibernate.AbstractHibernateDAO;
import com.wiecia.springtest.db.model.Car;

@Repository
// ("carDao")
public class CarDAO extends AbstractHibernateDAO<Car> {

	@SuppressWarnings("unchecked")
	public List<Car> getAllFords() {
		Criteria crit = this.getSession().createCriteria(Car.class);
		crit.add(Restrictions.eq("mark", "Ford"));
		return crit.list();
	}

}
