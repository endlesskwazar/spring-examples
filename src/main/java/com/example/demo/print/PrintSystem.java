package com.example.demo.print;

public class PrintSystem {
	
	public PrintSystem(Printable source) {
		this.source = source;
	}
	
	private Printable source;

	public String print() {
		return source.getTextToPrint();
	}

}
