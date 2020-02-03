package com.example.demo.print;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class PrintSystem {
	
	public PrintSystem(Printable source) {
		this.source = source;
	}
	
	@Autowired
	@Qualifier("statistic")
	private Printable source;

	public String print() {
		return source.getTextToPrint();
	}

}
