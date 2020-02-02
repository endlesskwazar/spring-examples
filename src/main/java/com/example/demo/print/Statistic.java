package com.example.demo.print;

public class Statistic implements Printable {
	
	private final Cnf cnf;
	
	public Statistic(Cnf cnf) {
		this.cnf= cnf;
	}

	@Override
	public String getTextToPrint() {
		return "Statistic for some date ranges" + cnf.getInfo();
	}

}
