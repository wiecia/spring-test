package com.wiecia.springtest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wiecia.springtest.db.GenericDAO;
import com.wiecia.springtest.db.dao.CarDAO;
import com.wiecia.springtest.db.model.Animal;
import com.wiecia.springtest.db.model.Person;

@Service
@Transactional
public class PersonService {

	GenericDAO<Person> dao;

	GenericDAO<Animal> animalDao;

	// @Qualifier("carDao")
	@Autowired()
	CarDAO carDao;

	@Autowired
	public void setDao(GenericDAO<Person> daoToSet) {
		this.dao = daoToSet;
		this.dao.setClazz(Person.class);
	}

	public GenericDAO<Animal> getAnimalDao() {
		return animalDao;
	}

	@Autowired
	public void setAnimalDao(GenericDAO<Animal> animalDao) {
		this.animalDao = animalDao;
		this.animalDao.setClazz(Animal.class);
	}

	public CarDAO getCarDao() {
		return carDao;
	}

	public void save(Person person) {
		dao.save(person);
	}

	@Transactional(readOnly = true)
	public List<Person> getAll() {
		return dao.findAll();
	}
}
