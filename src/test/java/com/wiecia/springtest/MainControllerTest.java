package com.wiecia.springtest;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.wiecia.springtest.db.model.Car;
import com.wiecia.springtest.web.MainController;

//@RunWith(SpringJUnit4ClassRunner.class)
@RunWith(MockitoJUnitRunner.class)
// @ContextConfiguration
public class MainControllerTest extends TestCase {

	@Mock
	// @InjectMocks
	MainController mainController;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldReturnJsonData() {
		// given
		// mainController = new MainController();

		// when
		List<Car> cars = mainController.getFords();

		// then
		assertTrue(cars != null);
	}
}
