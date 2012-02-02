package com.wiecia.springtest;

import junit.framework.TestCase;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wiecia.springtest.config.ApplicationConfig;
import com.wiecia.springtest.config.PersistenceConfig;
import com.wiecia.springtest.db.model.Car;
import com.wiecia.springtest.service.PersonService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfig.class, PersistenceConfig.class })
public class EntityTest extends TestCase {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	PersonService personService;

	@Test
	public void testAutowiring() {
		assertNotNull(sessionFactory);
		assertNotNull(personService);
	}

	@Test
	public void shouldLoadCarsFromDb() {
		// given
		Car car = new Car();
		car.setMark("Ford");
		car.setCarModel("Mondeo");
		// personService.getCarDao().save(car);
		sessionFactory.getCurrentSession().save(car);

		// when
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Car.class);
		crit.add(Restrictions.eq("mark", "Ford"));

		// then
		assertTrue(crit.list().size() == 1);
	}

}
