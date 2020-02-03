package com.example.demo.print;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class Cnf {
	
	private final LocalDateTime current;
	
	public Cnf() {
		current = LocalDateTime.now();
	}
	
	public String getInfo() {
		return "Generated on " + current.toString();
	}
}
