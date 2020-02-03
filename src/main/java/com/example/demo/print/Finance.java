package com.example.demo.print;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class Finance implements Printable {

	@Override
	public String getTextToPrint() {
		return "Some finance data";
	}

}
