package com.example.demo;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@Order(1)
@Profile("jdbc")
public class InititJdbTemplateTables implements CommandLineRunner {
	
	
	private static final Logger log = LoggerFactory.getLogger(InititJdbTemplateTables.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void run(String... args) throws Exception {
		log.info("Creating tables...");
		
		jdbcTemplate.execute("DROP TABLE products IF EXISTS");
		jdbcTemplate.execute("CREATE TABLE products (id INT AUTO_INCREMENT  PRIMARY KEY, title VARCHAR(250) NOT NULL, price DECIMAL(20, 5) NOT NULL, description VARCHAR(250) NOT NULL);");
		
		log.info("Seeding data...");
		
		jdbcTemplate.update("INSERT INTO products (title, price, description) VALUES(?,?,?)",
                new Object[] { "Samsung galaxy Tab 2", new BigDecimal(8000), "Phone or tablet." });
		jdbcTemplate.update("INSERT INTO products (title, price, description) VALUES(?,?,?)",
                new Object[] { "DELL XPS 13", new BigDecimal(40000), "Business laptop." });
	}

}
