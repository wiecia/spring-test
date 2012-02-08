package com.wiecia.springtest.web;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wiecia.springtest.db.model.Animal;
import com.wiecia.springtest.db.model.Animal.AnimalType;
import com.wiecia.springtest.db.model.Car;
import com.wiecia.springtest.db.model.Person;
import com.wiecia.springtest.service.PersonService;

@Controller
public class MainController {

	private static Logger LOG = LoggerFactory.getLogger(MainController.class);

	@Autowired
	private PersonService personService;

	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String index(ModelMap model) {
		LOG.info("Inside MainConotroller index method!");
		model.addAttribute("people", personService.getAll());
		return "index";
	}

	@RequestMapping(value = "/addStuff", method = RequestMethod.POST)
	public String addStuff(HttpSession session, ModelMap model,
			RedirectAttributes redirect) {
		Person p = new Person();
		p.setAge(RandomUtils.nextInt(90));
		p.setName("John Doe");
		p.setEmail(RandomStringUtils.randomAlphanumeric(8) + "@example.com");
		personService.save(p);

		Animal animal = new Animal();
		animal.setName("Fluffy-" + RandomStringUtils.randomAlphanumeric(3));

		animal.setType(AnimalType.values()[new Random().nextInt(AnimalType
				.values().length)]);
		personService.getAnimalDao().save(animal);
		LOG.info(animal.toString());

		Car car = new Car();
		car.setMark("Ford");
		car.setCarModel(RandomStringUtils.randomAlphabetic(6));
		personService.getCarDao().save(car);

		Car car2 = new Car();
		car2.setMark("Nissan");
		car2.setCarModel(RandomStringUtils.randomAlphabetic(4));
		personService.getCarDao().save(car2);

		// List<Car> cars = personService.getCarDao().getAllFords();
		// for (Car c : cars) {
		// LOG.info(c.toString());
		// }
		redirect.addFlashAttribute("redirectMessage",
				"Some stuff has been successfuly added!");
		return "redirect:/";
		// return "index";
	}

	@RequestMapping(value = "/getFords")
	@ResponseBody
	public List<Car> getFords() {
		return personService.getCarDao().getAllFords();
	}

	@RequestMapping("clearDb")
	public String clearDb(HttpSession session, RedirectAttributes redirect) {
		personService.getAnimalDao().delete(
				personService.getAnimalDao().findAll());
		personService.getCarDao().delete(personService.getCarDao().findAll());
		personService.deleteAll();
		redirect.addFlashAttribute("redirectMessage",
				"Database has been cleared out!");
		return "redirect:/";
	}
}
