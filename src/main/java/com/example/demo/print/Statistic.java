package com.example.demo.print;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("statistic")
public class Statistic implements Printable {
	
	@Autowired
	private final Cnf cnf;
	
	public Statistic(Cnf cnf) {
		this.cnf= cnf;
	}

	@Override
	public String getTextToPrint() {
		return "Statistic for some date ranges" + cnf.getInfo();
	}

}
