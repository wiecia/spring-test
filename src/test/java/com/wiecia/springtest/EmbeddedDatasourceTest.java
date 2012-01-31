package com.wiecia.springtest;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

public class EmbeddedDatasourceTest extends TestCase {

	private static final Logger log = LoggerFactory
			.getLogger(EmbeddedDatasourceTest.class);

	private EmbeddedDatabase db;

	@Override
	@Before
	public void setUp() {
		db = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
				.addScript("scripts/schema.sql")
				.addScript("scripts/addCars.sql").setName("springtestdb")
				.build();
	}

	@Test
	public void testDatabase() {
		// given
		JdbcTemplate tpl = new JdbcTemplate(db);

		// when
		long rows = tpl.queryForLong("select count(*) from car");
		log.info("Number of cars: " + rows);

		// then
		assertTrue(rows > 0);
	}

	@After
	public void teadDown() {
		db.shutdown();
	}
}
